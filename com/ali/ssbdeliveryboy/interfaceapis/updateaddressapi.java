package com.ali.ssbdeliveryboy.interfaceapis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

public interface updateaddressapi {
    @FormUrlEncoded
    @PUT(".")
    Call<ResponseBody> updateaddress(@Field("id") String id, @Field("address") String address);

}
