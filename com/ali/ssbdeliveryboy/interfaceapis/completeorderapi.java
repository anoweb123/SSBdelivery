package com.ali.ssbdeliveryboy.interfaceapis;

import com.ali.ssbdeliveryboy.modelclasses.modelcompleteorder;
import com.ali.ssbdeliveryboy.modelclasses.modelorders;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface completeorderapi {
    @GET(".")
    Call<List<modelcompleteorder>> list();
}
