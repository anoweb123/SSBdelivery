package com.ali.ssbdeliveryboy.interfaceapis;

import com.ali.ssbdeliveryboy.modelclasses.modelorderitem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface showorderitemapi {
    @GET(".")
    Call<List<modelorderitem>> list();
}
