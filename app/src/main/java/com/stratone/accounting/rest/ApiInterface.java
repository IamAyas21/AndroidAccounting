package com.stratone.accounting.rest;

import com.stratone.accounting.response.ResponseCashFlow;
import com.stratone.accounting.response.ResponseCashFlowTotal;
import com.stratone.accounting.response.ResponseDashboard;
import com.stratone.accounting.response.ResponseProfitLossClass1;
import com.stratone.accounting.response.ResponseProfitLossClass2;
import com.stratone.accounting.response.ResponseReb;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface  ApiInterface {
    @FormUrlEncoded
    @POST("api/Dashboard/ChartCashFlow")
    Call<ResponseDashboard> ChartCashFlow(
            @Field("userId") String userId,
            @Field("year") String year
    );

    @FormUrlEncoded
    @POST("api/CashFlow/List")
    Call<ResponseCashFlow> CashFlow(
            @Field("userId") String userId,
            @Field("startDate") String startDate,
            @Field("endDate") String endDate
    );

    @FormUrlEncoded
    @POST("api/CashFlow/Total")
    Call<ResponseCashFlowTotal> CashFlowTotal(
            @Field("userId") String userId,
            @Field("startDate") String startDate,
            @Field("endDate") String endDate
    );

    @FormUrlEncoded
    @POST("api/Reb/List")
    Call<ResponseReb> Reb(
            @Field("userId") String userId,
            @Field("startDate") String startDate,
            @Field("endDate") String endDate
    );

    @FormUrlEncoded
    @POST("api/ProfitLoss/Class1")
    Call<ResponseProfitLossClass1> ProfitLossClass1(
            @Field("userId") String userId,
            @Field("startDate") String startDate,
            @Field("endDate") String endDate
    );

    @FormUrlEncoded
    @POST("api/ProfitLoss/Class2")
    Call<ResponseProfitLossClass2> ProfitLossClass2(
            @Field("userId") String userId,
            @Field("startDate") String startDate,
            @Field("endDate") String endDate,
            @Field("classId") String classId
    );

    @FormUrlEncoded
    @POST("api/ProfitLoss/Class3")
    Call<ResponseProfitLossClass2> ProfitLossClass3(
            @Field("userId") String userId,
            @Field("startDate") String startDate,
            @Field("endDate") String endDate,
            @Field("classId") String classId
    );
}
