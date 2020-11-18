package com.ali.ssbdeliveryboy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ali.ssbdeliveryboy.holderclasses.holdershops;
import com.ali.ssbdeliveryboy.interfaceapis.selectshopapi;
import com.ali.ssbdeliveryboy.interfaceapis.signupapi;
import com.ali.ssbdeliveryboy.modelclasses.modelshops;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class selectshop extends AppCompatActivity implements holdershops.onselect{


    ImageView back,tick;
    holdershops adapter;
    public static String mysharedpref="mysharedpref";
    RecyclerView rec;
    List<modelshops> models;
    String name,shopid,email,pass,address,phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectshop);

        email=getIntent().getExtras().getString("email");
        pass=getIntent().getExtras().getString("password");
        address=getIntent().getExtras().getString("address");
        phone=getIntent().getExtras().getString("phone");



        rec=findViewById(R.id.rec);
        tick=findViewById(R.id.tick);
        back=findViewById(R.id.back);

        SharedPreferences prefs = getSharedPreferences(mysharedpref, MODE_PRIVATE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(selectshop.this,signup.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (prefs.getString("ontick","").equals("")){
                    Toast.makeText(selectshop.this, "You selected nothing", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://"+prefs.getString("ipv4","10.0.2.2")+":5000/dboy/signup/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                signupapi api=retrofit.create(signupapi.class);
                Call<ResponseBody> listCall=api.responsesignup(email,shopid,pass,"N/A",name,address,phone);

                listCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast.makeText(selectshop.this, ((String.valueOf(response.code())) ), Toast.LENGTH_SHORT).show();
                        if (response.isSuccessful()){
                            Toast.makeText(selectshop.this, "Registered", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                    }
                });
            }
        });

        models = new ArrayList<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://"+prefs.getString("ipv4","10.0.2.2")+":5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        selectshopapi api=retrofit.create(selectshopapi.class);
        Call<List<modelshops>> listCall=api.list();

        listCall.enqueue(new Callback<List<modelshops>>() {
            @Override
            public void onResponse(Call<List<modelshops>> call, Response<List<modelshops>> response) {
                if (response.isSuccessful()){
                    models=response.body();
                    adapter = new holdershops( selectshop.this,models);
                    rec.setHasFixedSize(true);
                    final LinearLayoutManager manager=new LinearLayoutManager(selectshop.this);
                    rec.setLayoutManager(manager);
                    rec.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    adapter.onclick(selectshop.this);
                }
            }
            @Override
            public void onFailure(Call<List<modelshops>> call, Throwable t) {
                Toast.makeText(selectshop.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onselect(String name, String shopid) {
        this.name=name;
        this.shopid=shopid;

    }
}