package com.ali.ssbdeliveryboy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class signup extends AppCompatActivity {

    LinearLayout login;
    CircleImageView profileimage;
    FloatingActionButton floatingActionButton;
    Button selectshop;

    EditText name,email,pass,copass,phone,address;
    String sname,semail,spass,scopass,sphone,saddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        login=findViewById(R.id.login);
        selectshop=findViewById(R.id.selectshop);


        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        copass=findViewById(R.id.conpass);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup.this, logindboypage.class));
                overridePendingTransition( R.style.Animation_Design_BottomSheetDialog, R.style.Animation_Design_BottomSheetDialog );
            }
        });

        selectshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(signup.this, selectshop.class);

                sname=name.getText().toString();
                semail=email.getText().toString();
                saddress=address.getText().toString();
                sphone=phone.getText().toString();
                spass=pass.getText().toString();
                scopass=copass.getText().toString();

                if (sname.isEmpty()){
                    name.setError("Enter name");
                }

                if (semail.isEmpty()){
                    email.setError("Enter Email");
                }
                if (spass.isEmpty()){
                    pass.setError("Set password");
                }
                if (scopass.isEmpty()){
                    copass.setError("Enter password again");
                }
                if (saddress.isEmpty()){
                    address.setError("Enter address");
                }
                if (sphone.isEmpty()){
                    phone.setError("Enter Phone Number");
                }

                if (sname.isEmpty()||semail.isEmpty()||scopass.isEmpty()||spass.isEmpty()||sphone.isEmpty()||saddress.isEmpty()){}
                else {
                    intent.putExtra("name",sname);
                    intent.putExtra("email",semail);
                    intent.putExtra("password",spass);
                    intent.putExtra("image","");
                    intent.putExtra("address",saddress);
                    intent.putExtra("phone",sphone);
                    startActivity(intent);
                }
            }
        });


    }
}