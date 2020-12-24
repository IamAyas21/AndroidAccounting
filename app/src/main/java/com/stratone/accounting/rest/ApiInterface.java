package com.stratone.accounting.rest;

import com.stratone.accounting.response.ResponseCashFlow;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface ApiInterface {
    @FormUrlEncoded
    @GET("api/Dashboard/ChartCashFlow")
    Call<ResponseCashFlow> ChartCashFlow(
            @Field("userId") String userId,
            @Field("year") String year
    );
}
