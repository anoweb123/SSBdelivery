package com.ali.ssbdeliveryboy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class splash extends AppCompatActivity {

    private  static String mysharedpref="mysharedpref";
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences.Editor editor=getSharedPreferences(mysharedpref,MODE_PRIVATE).edit();
        editor.putString("ipv4","192.168.0.104");
        editor.putString("ontick","");
        editor.apply();

        start=findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(splash.this,logindboypage.class));
            }
        });
    }
}