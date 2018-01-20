package com.globati.service;

import com.globati.service.exceptions.ServiceException;
import com.globati.utildb.SendMail;
import org.springframework.stereotype.Service;
import com.globati.mail.beans.WelcomeToGlobati;
import java.util.List;

@Service
public class ScheduledTaskService {

    //http://pojo.sodhanalibrary.com/string.html
//    @Scheduled(cron = "0 35 4 * * ?")
    public void sendMarketingMail() throws Exception {
//        List<Event> events = getAllActiveEvents();
//
//        for(Event e:events){
//            if( DateTools.dateIsBeforeCurrentDate(e) && e.isActive() ){
//                e.setActive(false);
//                updateEvent(e);
//            }
//        }

        SendMail.sendWelcomeToGlobatiMail("daniel@Globati.com", WelcomeToGlobati.getMail());




    }
}
