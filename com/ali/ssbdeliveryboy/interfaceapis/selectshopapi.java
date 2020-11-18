package com.ali.ssbdeliveryboy.interfaceapis;

import com.ali.ssbdeliveryboy.modelclasses.modelshops;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface selectshopapi {
    @GET("adminlogin/users")
    Call<List<modelshops>> list();
}
