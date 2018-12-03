package com.globati.request;

/**
 * Created by daniel on 1/25/17.
 */
public class ChangePasswordWithToken {

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
        return "ChangePasswordWithToken{" +
                "token='" + token + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
