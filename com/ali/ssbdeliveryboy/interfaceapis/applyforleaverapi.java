package com.ali.ssbdeliveryboy.interfaceapis;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface applyforleaverapi {
    @FormUrlEncoded
    @POST("/delivery/addLeave")
    Call<ResponseBody> list(@Field("detail") String detail,@Field("deliveryBoyId") String dboyid,@Field("shopId") String shopid);
}
