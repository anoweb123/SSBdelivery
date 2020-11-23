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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class signup extends AppCompatActivity {

    Button selectshop;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(signup.this, logindboypage.class));
        overridePendingTransition( R.style.Animation_Design_BottomSheetDialog, R.style.Animation_Design_BottomSheetDialog );

    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static final Pattern VALID_password =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{7,}$", Pattern.CASE_INSENSITIVE);




    EditText name,email,pass,copass,phone,address;
    String sname,semail,spass,scopass,sphone,saddress;

    TextView signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        selectshop=findViewById(R.id.selectshop);

        signin=findViewById(R.id.signin);


        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        copass=findViewById(R.id.conpass);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);




        signin.setOnClickListener(new View.OnClickListener() {
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



                if (sname.isEmpty()||semail.isEmpty()||scopass.isEmpty()||spass.isEmpty()||sphone.isEmpty()||saddress.isEmpty()){
                }

                else {
                    Boolean a= validate(semail);
                    if (a){
                        Boolean ab=validatepass(spass);
                        if (ab){
                    intent.putExtra("name",sname);
                    intent.putExtra("email",semail);
                    intent.putExtra("password",spass);
                    intent.putExtra("image","");
                    intent.putExtra("address",saddress);
                    intent.putExtra("phone",sphone);
                    startActivity(intent);}
                        else {
                            Toast.makeText(signup.this, "Password should contain 1 digit,1 uppercase,1 lowercase and 1 special character", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        Toast.makeText(signup.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validatepass(String pass) {
        Matcher matcher = VALID_password.matcher(pass);
        return matcher.find();
    }
}