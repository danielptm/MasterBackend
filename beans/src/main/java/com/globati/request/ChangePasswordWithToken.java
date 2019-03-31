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

    private String email;
    private String token;
    private String password;
    private String oldPassword;

    ChangePasswordWithToken(){}

    ChangePasswordWithToken(String email, String token, String password, String oldPassword){
        this.token = token;
        this.password = password;
        this.oldPassword = oldPassword;
        this.email = email;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
            LOGGER.error("ChangePasswordWithToken exception: ");
            e.printStackTrace();
        }
        return toReturn;
    }


}
