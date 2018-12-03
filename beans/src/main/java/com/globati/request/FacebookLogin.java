package com.globati.request;

/**
 * Created by daniel on 4/5/17.
 */
public class FacebookLogin {


    private String name;
    private String userId;
    private String email;
    private String image;
    private String token;

    FacebookLogin(){}

    FacebookLogin(String name, String email, String userid, String image, String token){
        this.name = name;
        this.email = email;
        this.userId = userid;
        this.image = image;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUserid() {
        return userId;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "FacebookLogin{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", token='" + token + '\'' +
                '}';
    }


}
