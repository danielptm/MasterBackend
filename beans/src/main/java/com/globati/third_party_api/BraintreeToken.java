package com.globati.third_party_api;

/**
 * Created by daniel on 1/26/17.
 */
public class BraintreeToken {

    String nonce;

    public BraintreeToken(String token){
        this.nonce = token;
    }

    public BraintreeToken(){}

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

}
