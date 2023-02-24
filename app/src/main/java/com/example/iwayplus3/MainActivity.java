package com.example.iwayplus3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iwayplus3.ModelResponse.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText input_email,input_password;
Button button_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_email=findViewById(R.id.input_email);
        input_password=findViewById(R.id.input_password);
        button_signup=findViewById(R.id.button_signup);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String useremail=input_email.getText().toString();
        String userPasswword=input_password.getText().toString();

        // checking validation
        if(useremail.isEmpty())
        {
            input_email.requestFocus();
            input_email.setError("Please Enter your Email");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches())
        {
            input_email.requestFocus();
            input_email.setError("Please Enter Correct Email");
            return;
        }

        if(userPasswword.isEmpty())
        {
            input_password.requestFocus();
            input_password.setError("Please Enter your Password");
            return;
        }
        if(userPasswword.length()<8)
        {
            input_password.requestFocus();
            input_password.setError("Please Enter Correct Password");
            return;
        }

        Call<RegisterResponse> call=RetrofitClient
                .getInstance()
                .getApi()
                .register(useremail,userPasswword);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                // if we get any response from the api
                RegisterResponse registerResponse=response.body();
                if(response.isSuccessful())
                {
                    if(registerResponse.getExist()=="true")
                    {
                        Toast.makeText(MainActivity.this,registerResponse.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    else
                    Toast.makeText(MainActivity.this,registerResponse.getMessage(),Toast.LENGTH_SHORT).show();


                }
                else
                {
                   // System.out.println(response);
                    Toast.makeText(MainActivity.this,registerResponse.getError(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
// if api cannot be connected
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}