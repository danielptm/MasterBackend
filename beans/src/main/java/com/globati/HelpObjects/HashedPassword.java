package com.globati.HelpObjects;

/**
 * Created by daniel on 1/13/17.
 */
public class HashedPassword {

    private char[] password;
    private byte[] salt;
    int iterations;
    int keylength;

    public HashedPassword(char[] password, byte[] salt, int iterations, int keylength){
        this.password = password;
        this.salt = salt;
        this.iterations = iterations;
        this.keylength = keylength;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public int getKeylength() {
        return keylength;
    }

    public void setKeylength(int keylength) {
        this.keylength = keylength;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    @Override
    public String toString() {
        return "HashedPassword{" +
                "password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", iterations=" + iterations +
                ", keylength=" + keylength +
                '}';
    }
}
