package com.ali.ssbdeliveryboy.interfaceapis;

import com.ali.ssbdeliveryboy.modelclasses.modelorders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface showorderapi {
@GET(".")
    Call<List<modelorders>> list();
}
