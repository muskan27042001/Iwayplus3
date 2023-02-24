package com.example.iwayplus3.tokenmanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;

public class TokenManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int Mode=0;
    private static final String REFNAME="JWTTOKENSESSION";
    private static final String KEY_USER_NAME="username";
    private static final String KEY_JWT_TOKEN="jwttoken";
    private Context context;

    public TokenManager(Context context)
    {
        this.context=context;
        sharedPreferences=context.getSharedPreferences(REFNAME,Mode);
        editor=sharedPreferences.edit();
    }

    public void createSession(String username,String jwtvalue)
    {
        editor.putString(KEY_USER_NAME,username);
        editor.putString(KEY_JWT_TOKEN,jwtvalue);
        editor.commit();
    }
}
