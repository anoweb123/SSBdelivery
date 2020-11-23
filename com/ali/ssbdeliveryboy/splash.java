package com.ali.ssbdeliveryboy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class splash extends AppCompatActivity {

    private static String mysharedpref="mysharedpref";
    Button start;
    ExtendedFloatingActionButton signup,started;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences.Editor editor=getSharedPreferences(mysharedpref,MODE_PRIVATE).edit();
        editor.putString("ipv4","192.168.43.148");
        editor.putString("ontick","");
        editor.apply();

        SharedPreferences sharedPreferences=getSharedPreferences(mysharedpref,MODE_PRIVATE);
        if (sharedPreferences.getString("status","").equals("login")){
            startActivity(new Intent(splash.this,dashboard.class));
        }


        signup=findViewById(R.id.signup);
        started=findViewById(R.id.get);

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
}