package com.ali.ssbdeliveryboy.interfaceapis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface signupapi {
    @FormUrlEncoded
    @POST(".")
    Call<ResponseBody> responsesignup(
            @Field("email") String email,
            @Field("shopId") String shopid,
            @Field("password") String password,
            @Field("sallary") String sallary,
            @Field("name") String name,
            @Field("address") String address,
            @Field("cell") String cell
    );
}
