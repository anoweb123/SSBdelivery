package com.ali.ssbdeliveryboy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ali.ssbdeliveryboy.interfaceapis.loginapi;
import com.ali.ssbdeliveryboy.modelclasses.modellogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ali.ssbdeliveryboy.selectshop.mysharedpref;

public class logindboypage extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    private  static String mysharedpref="mysharedpref";
    Button login;
    modellogin models;
    EditText email,pass;
    String semail,spass;
    ProgressBar bar;
    LinearLayout signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logindboypage);

        bar=findViewById(R.id.bar);
        signup=findViewById(R.id.signup);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        login=findViewById(R.id.login);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(logindboypage.this, com.ali.ssbdeliveryboy.signup.class));
                overridePendingTransition( R.style.Animation_Design_BottomSheetDialog, R.style.Animation_Design_BottomSheetDialog );
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                semail=email.getText().toString();
                spass=pass.getText().toString();

                bar.setVisibility(View.VISIBLE);


                SharedPreferences prefs =getSharedPreferences(mysharedpref, MODE_PRIVATE);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://"+prefs.getString("ipv4","10.0.2.2")+":5000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                loginapi api=retrofit.create(loginapi.class);
                Call<modellogin> listCall=api.list(semail,spass);

                listCall.enqueue(new Callback<modellogin>() {

                    @Override
                    public void onResponse(@Nullable Call<modellogin> call,@Nullable Response<modellogin> response) {

                        assert response != null;
                        if (response.isSuccessful()){
                            bar.setVisibility(View.INVISIBLE);

                            SharedPreferences.Editor sharedPreferences=getSharedPreferences(mysharedpref,MODE_PRIVATE).edit();
                            assert response.body() != null;
                            sharedPreferences.putString("dboyid",response.body().get_id());
                            sharedPreferences.putString("address",response.body().getAddress());
                            sharedPreferences.putString("phone",response.body().getCell());
                            sharedPreferences.putString("email",response.body().getEmail());
                            sharedPreferences.putString("password",response.body().getPassword());
                            sharedPreferences.putString("name",response.body().getName());
                            sharedPreferences.putString("shopid",response.body().getShopId());
                            sharedPreferences.putString("status",response.body().getStatus());
                            sharedPreferences.putString("image",response.body().getImage());
                            sharedPreferences.apply();

                            if (response.body().getStatus().equals("pending")){
                                Toast.makeText(logindboypage.this, "Not approved yet", Toast.LENGTH_SHORT).show();
                            }
                            else {
                            Toast.makeText(logindboypage.this, "Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(logindboypage.this, dashboard.class));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<modellogin> call, Throwable t) {
                        Toast.makeText(logindboypage.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });
    }
}