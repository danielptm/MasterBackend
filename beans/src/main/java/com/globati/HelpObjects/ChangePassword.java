package com.globati.HelpObjects;

import com.globati.dynamodb.converters.lists.DynamoRecommendationListConverter;
import com.globati.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by daniel on 1/23/17.
 */
public class ChangePassword {

    private static final Logger LOGGER = LogManager.getLogger(ChangePassword.class);

    String email;
    String oldPassword;
    String newPassword;

    public ChangePassword(String email, String oldPassword, String newPassword){
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ChangePassword(){}


    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String toReturn = null;
        try {
            toReturn = Mapper.getMapper().writeValueAsString(this);
        } catch (Exception e) {
            LOGGER.error("ChangePassword exception: ");
            e.printStackTrace();
        }
        return toReturn;
    }
}
