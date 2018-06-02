package com.globati.service;
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.EmployeeInfo;
import com.globati.dbmodel.Recommendation;
import com.globati.deserialization_beans.response.employee.AutoCompleteEmployee;
import com.globati.enums.Verified;
import com.globati.mail.beans.HelpRecommendationPrompt;
import com.globati.utildb.SendMail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Scheduled(cron = "0 15 11 * * 4")
    public void sendMarketingMail() throws Exception {
        log.info("** Creating list for AutoCompleteEmployees **");
        List<EmployeeInfo> employeeInfos = employeeInfoService.getAllEmployeeInfos();

        for(EmployeeInfo info: employeeInfos){
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