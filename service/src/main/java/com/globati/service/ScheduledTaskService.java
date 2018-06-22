package com.globati.service;
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.EmployeeInfo;
import com.globati.dbmodel.Recommendation;
import com.globati.enums.Verified;
import com.globati.mail.beans.GlobatiReminder;
import com.globati.mail.beans.HelpRecommendationPrompt;
import com.globati.utildb.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledTaskService {

    private static final Logger log = LogManager.getLogger(EmployeeService.class);


    @Autowired
    PropertiesService propertiesService;

    @Autowired
    JwtService jwtService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    EmployeeInfoService employeeInfoService;

    //http://pojo.sodhanalibrary.com/string.html
    @Scheduled(cron = "0 30 10 * * SAT")
    public void sendMarketingMail() throws Exception {
        log.info("** Creating list for AutoCompleteEmployees **");
        List<EmployeeInfo> employeeInfos = employeeInfoService.getAllEmployeeInfos();

        for(EmployeeInfo info: employeeInfos){
            if(info.getEmployeeId() == 1){
                Employee employee = employeeService.getEmployeeById(info.getEmployeeId());
                sendHelpRecommendationPrompt(employee, info);
            }
            if( info.get_verified().equals(Verified.STANDARD) ){
                Employee employee = employeeService.getEmployeeById(info.getEmployeeId());
                List<Recommendation> recommendations = recommendationService.getRecommendationByEmployeeId(employee.getId());
                if(recommendations.size() < 5) {
                    log.info("Sending prompt mail");
                    SendMail.sendCustomMailToGlobatiStaff("daniel@globati.com", "HelpRecommendation prompt email is on a scheduled task, and was just sent to "+employee.getGlobatiUsername());
                    sendHelpRecommendationPrompt(employee, info);
                }
            }
        }
    }

    @Scheduled(cron = "0 38 12 1 * ?")
    public void sendReminder() throws Exception {
        log.info("** SENDING REMINDER **");
        List<EmployeeInfo> employeeInfos = employeeInfoService.getAllEmployeeInfos();

        for(EmployeeInfo info: employeeInfos){
            if(info.getEmployeeId() == 1){
                Employee employee = employeeService.getEmployeeById(info.getEmployeeId());
                sendGlobatiReminder(employee, info);
            }
            if( info.get_verified().equals(Verified.STANDARD) ){
                Employee employee = employeeService.getEmployeeById(info.getEmployeeId());
                sendGlobatiReminder(employee, info);
            }
        }
    }

    public void sendGlobatiReminder(Employee employee, EmployeeInfo employeeInfo) throws Exception {
        String user = employee.getGlobatiUsername();
        String email = employee.getEmail();
        String flyer = employee.getFlyerLink();
        GlobatiReminder gm = new GlobatiReminder();
        SendMail.sendGlobatiReminder(user, email, gm.getMessage(user, employee.getFlyerLink()));

    }

    public boolean sendHelpRecommendationPrompt(Employee employee, EmployeeInfo employeeInfo){
        String url = null;
        String production = "https://about.globati.com";
        String dev = "http://localhost:4200";

        String urlCredentials = "/login/"+employee.getGlobatiUsername()+"/"+ jwtService.buildJwt(employeeInfo.getAuthToken());

        String email = employee.getEmail();

        if (System.getenv("GLOBATI_SERVER_ENV").equals("production")){
            url = production + urlCredentials;
        } else {
            url = dev + urlCredentials;
        }
        HelpRecommendationPrompt hrp = new HelpRecommendationPrompt(employee.getGlobatiUsername(), url);
        try {
            SendMail.sendHelpRecommendationPromp(employee.getGlobatiUsername(), email, hrp.getMailText());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}