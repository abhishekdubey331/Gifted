package com.gifted.app.giftededucation.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class EncryptPassword {


    public static String md5(String s) {

          /*  // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (byte aMessageDigest : messageDigest)
                hexString.append(Integer.toHexString(0xFF & aMessageDigest));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }*/
        return s;
    }

}
