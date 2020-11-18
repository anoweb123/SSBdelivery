package com.ali.ssbdeliveryboy.interfaceapis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PUT;

public interface updatepassapi {
    @FormUrlEncoded
    @PUT(".")
    Call<ResponseBody> updatepass(@Field("id") String id, @Field("password") String pass);

}
