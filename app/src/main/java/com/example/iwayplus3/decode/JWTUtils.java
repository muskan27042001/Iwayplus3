package com.example.iwayplus3.decode;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;


public class JWTUtils {

    public static void decodeJWT(String EncodeString) throws Exception {
        String[] splitstr = EncodeString.split("\\.");
        Log.d("kkk", "Header" + getJSON(splitstr[0]));
        Log.d("kkk", "Payload" + getJSON(splitstr[1]));
    }

    public static String getJSON(String EncodeString) throws UnsupportedEncodingException {


        byte[] decodebyte = android.util.Base64.decode(EncodeString, android.util.Base64.URL_SAFE);
        return new String(decodebyte, "UTF_8");

    }
}