package com.ali.ssbdeliveryboy.interfaceapis;

import com.ali.ssbdeliveryboy.modelclasses.modellogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface loginapi {
    @FormUrlEncoded
    @POST("dboy/login")
    Call<modellogin> list(@Field("email") String email,@Field("password") String pass);
}
