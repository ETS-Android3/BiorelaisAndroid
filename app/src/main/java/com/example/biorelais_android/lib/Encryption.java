package com.example.biorelais_android.lib;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {

    // ---------------------------------------------
    // Hash en md5
    public static String md5(String mdp) {
        return hash(mdp, "MD5", 32);
    }
    // ---------------------------------------------



    // ---------------------------------------------
    // Hash en sha 256
    public static String sha256(String mdp) {
        return hash(mdp, "SHA-256", 64);
    }
    // ---------------------------------------------



    // ---------------------------------------------
    // Hash en un algo de hash
    private static String hash(String word, String algo, int size) {
        String hash = "";

        try {
            MessageDigest md = MessageDigest.getInstance(algo);
            byte[] messageDigest = md.digest(word.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hash = number.toString(16);
            while (hash.length() < size) {
                hash = "0" + hash;
            }
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hash;
    }
    // ---------------------------------------------
}
