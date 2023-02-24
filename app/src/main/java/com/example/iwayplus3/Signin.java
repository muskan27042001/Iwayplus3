package com.example.iwayplus3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwayplus3.ModelResponse.JWTToken;
import com.example.iwayplus3.ModelResponse.LoginResponse;
import com.example.iwayplus3.decode.JWTUtils;
import com.example.iwayplus3.tokenmanager.TokenManager;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class Signin extends AppCompatActivity {
    EditText input_signin_email,input_signin_password;
    Button button_signin;

    private TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        input_signin_email=findViewById(R.id.input_signin_email);
        input_signin_password=findViewById(R.id.input_signin_password);
        button_signin=findViewById(R.id.button_signin);
        tokenManager=new TokenManager(getApplicationContext());


        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loginUser();

                String useremail=input_signin_email.getText().toString();
                String userPasswword=input_signin_password.getText().toString();

                Call<JWTToken> jwtTokenCall=RetrofitClient.getInstance().getApi().userlogin(useremail,userPasswword);
                jwtTokenCall.enqueue(new Callback<JWTToken>() {
                    @Override
                    public void onResponse(Call<JWTToken> call, Response<JWTToken> response) {
                        JWTToken jwtToken=response.body();
                        tokenManager.createSession(useremail,jwtToken.getToken());

                        Call<JWTToken> usercall=RetrofitClient.getInstance().getApi().userlogin(useremail,userPasswword);
                        usercall.enqueue(new Callback<JWTToken>() {
                            @Override
                            public void onResponse(Call<JWTToken> call, Response<JWTToken> response) {

                                Toast.makeText(Signin.this,jwtToken.getToken(),Toast.LENGTH_SHORT).show();
                                Log.d("yeh","chalgya"+ jwtToken.getToken());
                                Log.d("yeh","chalgya"+ response.isSuccessful());
                                try {
                                    JWTUtils.decodeJWT(jwtToken.getToken());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                               // String token = response.body().getToken();
                               // try {
                                //    String ar= JWTUtils.getJSON(token);
                                  //  Log.d("yeh","ar");
                               // } catch (Exception e) {

                               // }
                            }

                            @Override
                            public void onFailure(Call<JWTToken> call, Throwable t) {

                            }
                        });

                        Toast.makeText(Signin.this,jwtToken.getToken(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<JWTToken> call, Throwable t) {
                        Toast.makeText(Signin.this,t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
/////////////////////////////////////////////////
   /* public static class JWTUtils {

        public static void decoded(String token) throws Exception {
            String[] split = token.split("\\.");
            //Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            //Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
        }

        private static String getJson(String strEncoded) throws UnsupportedEncodingException{
            byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
            return new String(decodedBytes, "UTF-8");
        }
    }*/
////////////////////////////////////////////////////////
    private void loginUser() {

        String useremail=input_signin_email.getText().toString();
        String userPasswword=input_signin_password.getText().toString();

        // checking validation
        if(useremail.isEmpty())
        {
            input_signin_email.requestFocus();
            input_signin_email.setError("Please Enter your Email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches())
        {
            input_signin_email.requestFocus();
            input_signin_email.setError("Please Enter Correct Email");
            return;
        }

        if(userPasswword.isEmpty())
        {
            input_signin_password.requestFocus();
            input_signin_password.setError("Please Enter your Password");
            return;
        }
        if(userPasswword.length()<8)
        {
            input_signin_password.requestFocus();
            input_signin_password.setError("Please Enter Correct Password");
            return;
        }

        Call<JWTToken> jwtTokenCall=RetrofitClient.getInstance().getApi().userlogin(useremail,userPasswword);
        jwtTokenCall.enqueue(new Callback<JWTToken>() {
            @Override
            public void onResponse(Call<JWTToken> call, Response<JWTToken> response) {
                JWTToken jwtToken=response.body();

                Toast.makeText(Signin.this,jwtToken.getToken(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JWTToken> call, Throwable t) {
                Toast.makeText(Signin.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

       /* Call<LoginResponse> call=RetrofitClient
                .getInstance()
                .getApi()
                .login(useremail,userPasswword);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse=response.body();

                if(response.isSuccessful())
                {
Intent intent=new Intent(Signin.this,Home.class);
intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(intent);
                    Toast.makeText(Signin.this,loginResponse.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Signin.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}