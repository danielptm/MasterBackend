package com.globati.request;

import com.globati.HelpObjects.ChangePassword;
import com.globati.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by daniel on 1/25/17.
 */
public class ChangePasswordWithToken {
    private static final Logger LOGGER = LogManager.getLogger(ChangePassword.class);

    String token;
    String password;

    ChangePasswordWithToken(){}

    ChangePasswordWithToken(String token, String password){
        this.token = token;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        String toReturn = null;
        try {
            toReturn = Mapper.getMapper().writeValueAsString(this);
        } catch (Exception e) {
            LOGGER.error("ChangePasswordWithToken exception: ");
            e.printStackTrace();
        }
        return toReturn;
    }


}
