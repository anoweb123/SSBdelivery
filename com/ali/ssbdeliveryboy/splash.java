package com.ali.ssbdeliveryboy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class splash extends AppCompatActivity {

    private final Handler mWaitHandler = new Handler();

    private static String mysharedpref="mysharedpref";
    Button start;
    ExtendedFloatingActionButton signup,started;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        signup=findViewById(R.id.signup);
        started=findViewById(R.id.get);

        signup.setVisibility(View.INVISIBLE);
        started.setVisibility(View.INVISIBLE);


        SharedPreferences.Editor editor=getSharedPreferences(mysharedpref,MODE_PRIVATE).edit();
        editor.putString("ipv4","192.168.43.148");
        editor.putString("ontick","");
        editor.apply();

        SharedPreferences sharedPreferences=getSharedPreferences(mysharedpref,MODE_PRIVATE);
        if (sharedPreferences.getString("status","").equals("login")){

            mWaitHandler.postDelayed(new Runnable() {
                @Override
                public void run() {

//                    signup.setVisibility(View.INVISIBLE);
//                    started.setVisibility(View.INVISIBLE);
                    startActivity(new Intent(splash.this,dashboard.class));
                }
            }, 2000);

        }
        else {
            signup.setVisibility(View.VISIBLE);
            started.setVisibility(View.VISIBLE);
        }


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(splash.this, signup.class));
            }
        });
      started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(splash.this,logindboypage.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}