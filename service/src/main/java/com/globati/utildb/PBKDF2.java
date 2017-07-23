package com.globati.utildb;

import com.globati.dbmodel.Employee;
import com.globati.dbmodel.EmployeeInfo;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by daniel on 1/13/17.
 */
public class PBKDF2 {

    private static final int ITERATIONS = 50000;
    private static final int KEY_LENGTH = 64*8;

    public static boolean checkPassword(EmployeeInfo employee, String passwordAttempt){
        String password = employee.get_globatiPassword();
        char[] passwordAttemptChar = passwordAttempt.toCharArray();
        byte[] salt = employee.get_salt();
        String hashedPassword =hashPassword(passwordAttemptChar, salt, ITERATIONS, KEY_LENGTH);
        if(password.equals(hashedPassword)){
            return true;
        }
        else{
            return false;
        }
    }

    public static EmployeeInfo hashEmployeePassword(EmployeeInfo e, String password){
        char[] charpass = password.toCharArray();
        byte[] salt = getsalt();
        String hashedPassword = hashPassword(charpass, salt, ITERATIONS, KEY_LENGTH);
        e.set_globatiPassword(hashedPassword);
        e.set_salt(salt);
        return e;
    }

    public static String hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return Base64.encodeBase64String(res);
        } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

    public static byte[] getsalt(){
        //Always use a SecureRandom generator
        SecureRandom sr = new SecureRandom();
        //Create array for salt
        byte[] salt = new byte[256];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}
