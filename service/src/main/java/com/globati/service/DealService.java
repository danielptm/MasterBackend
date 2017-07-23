package com.globati.service;



import com.globati.dbmodel.Deal;
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.Event;
import com.globati.repository.DealRepository;
import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.*;
import com.globati.utildb.HelpObjects.BusinessEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by daniel on 12/21/16.
 */
@Service
public class DealService {

    private static final Logger log = LogManager.getLogger(DealService.class);

    @Autowired
    private DealRepository dealRepository;


    @Autowired
    EmployeeService employeeService;

    DealService(){}

    public Deal createDeal(InputStream is, String title, String description, String location, double targetLat, double targetLong, Long id, String country, String street, String city, String dealtype, String website, String email, String plan, double cost, String transactionId, String billingStreet, String billingCity, String billingRegion, String billingCountry)  throws ServiceException, GlobatiUtilException {
        Deal deal=null;
        try {
            Employee employee = employeeService.getEmployeeById(id);
            String imagepath = ImageHandler.createNewImage(is);
            deal = new Deal(imagepath, title, description, location, targetLat, targetLong, employee, country, street, city, dealtype, website, email, plan, cost, transactionId, billingStreet, billingCity, billingRegion, billingCountry);
            Deal withDistanceDeal = CheckProximity.getDealProximity(deal, employee);
            return dealRepository.save(withDistanceDeal);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not create a deal: "+deal.toString(), e);
        }
    }

    public Deal updateDeal(Deal d) throws ServiceException {
        try{
            return dealRepository.save(d);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not update deal at this time: "+d.toString(),e);
        }
    }

    public List<Deal> getAllDealsForEmployeeId(Employee employee) throws ServiceException {
        try{
            return dealRepository.findDealsBy_employee_id(employee.get_id());
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not get deals for employee: "+employee.get_id(), e);
        }
    }

    /**
     * Only returns deals that are within the area, but not a deal that this employee recruited.
     * Because those deals are returned with getdealsbyEmployeeId.
     * @param country
     * @param id
     * @return
     * @throws ServiceException
     */
    public List<Deal> getNearbyActiveDeals(String country, Long id) throws ServiceException {
        List<Deal> nearbyDeals = new ArrayList<>();
        try{
            Employee employee = employeeService.getEmployeeById(id);
            List<Deal> deals = getActiveDealsByCountry(country);
            for(Deal deal:deals){
                Deal checkedDeal = CheckProximity.getNonRecruiterProximity(deal, employee);
                if( (checkedDeal.get_nonRecruiterDistance()<5) && (checkedDeal.get_nonRecruiterDistance() > 0.0) && (checkedDeal.get_employee().get_id().longValue() !=  employee.get_id().longValue()) ){
                    nearbyDeals.add(checkedDeal);
                }
            }
            return nearbyDeals;
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not get near by deals: "+country, e);
        }
    }

    /**
     * Select only active deals by country. The second paramter needs to be true to select active deals.
     * @param country
     * @return
     * @throws ServiceException
     */

    public List<Deal> getActiveDealsByCountry(String country) throws ServiceException {
        try{
            return dealRepository.getActiveDealsByCountry(country, true);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not get active deals by country: "+country, e);
        }
    }

    public List<Deal> getActiveDealsByEmployee(Long l) throws ServiceException {
        try{
            return dealRepository.getActiveDealsByEmployee(l, true);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not get active deals by employee with id: "+l, e);
        }
    }


    public List<Deal> getDealsCreatedByMonth(Integer month, Integer year, Long employeeId) throws ServiceException {
        try{
            return dealRepository.getDealsCreatedForMonth(month, year, employeeId);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not get deals created for month "+month, e);
        }
    }

    public void sendRecruitmentMail(Employee employee, String businessEmail, String businessName  ) throws ServiceException {
        try{
            SendMail.sendRecruitmentMail(employee, businessEmail, businessName);
        }catch(Exception e){
            log.error(e.toString());
            throw new ServiceException("Could not send recruitment mail", e);
        }
    }

    public List<Deal> getAllActiveDeals(){
        return this.dealRepository.getAllActiveDeals(true);
    }

    /**
     *
     * This function is called every day at 4.35 am. It gets all deals in the database. And checks each one to see if
     * it is expired. If it is expired, then active is set to false.
     *
     * @throws Exception
     */

    @Scheduled(cron = "35 4 * * * ?")
    public void getAllDealsAndSetExpiredEventsToNotActive() throws Exception {
        List<Deal> deals = getAllActiveDeals();

        for(Deal deal:deals){
            if( dealIsExpired(deal) && deal.is_active() ) {
                deal.set_active(false);
                updateDeal(deal);
            }
        }
    }

    /**
     * Check if the deal is expired or not.
     *
     * There is no test for this function yet.
     *
     *
     * @return
     */
    private boolean dealIsExpired(Deal deal) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(deal.get_datemade());
        if(deal.get_plan().equals("30days") || deal.get_plan().equals("30 day") || deal.get_plan().equals("30day")){
            c.add(Calendar.DAY_OF_MONTH, 30 );
        }
        else if(deal.get_plan().equals("60days") || deal.get_plan().equals("60 day") || deal.get_plan().equals("60day")){
            c.add(Calendar.DAY_OF_MONTH, 60);
        }
        else if(deal.get_plan().equals("90days") || deal.get_plan().equals("90 day") || deal.get_plan().equals("90day")){
            c.add(Calendar.DAY_OF_MONTH, 90);
        }
        else{
            throw new Exception("Invalid deal plan: Value should be '30 day', '60 day', or '90 day': value was: "+deal.get_plan());
        }
        return c.getTime().before( new Date() );
    }

}
