package com.globati.webmodel;

/**
 * Created by daniel on 5/14/17.
 *
 *
 *
 */
public class AWSCredentials {


    private final String accessKey = "AKIAJSYT5343PVMDHCRQ";
    private final String secretKey = "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";

    public String getAccessKey() {
        return accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public String toString() {
        return "AWSCredentials{" +
                "accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }


}
