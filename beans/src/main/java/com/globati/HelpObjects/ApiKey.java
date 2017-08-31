package com.globati.HelpObjects;

import java.util.UUID;

/**
 * Created by daniel on 1/16/17.
 */

public final class ApiKey {

    @Override
    public String toString() {
        return "ApiKey{" +
                "apiKey='" + apiKey + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    String apiKey;
    String time;

    public ApiKey(){
        this.time = new Long(System.currentTimeMillis()+14400*1000).toString();
        this.apiKey = UUID.randomUUID().toString();
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
