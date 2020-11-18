package com.ali.ssbdeliveryboy.interfaceapis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

public interface statuschangeapi {
    @FormUrlEncoded
    @PUT(".")
    Call<ResponseBody> status(@Field("id") String id, @Field("status") String status, @Field("deliverTo") String delto, @Field("deliveryBoy") String dboyid);
}
