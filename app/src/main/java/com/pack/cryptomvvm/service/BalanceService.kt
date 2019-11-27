package com.pack.cryptomvvm.service

import com.pack.cryptomvvm.model.Balance
import io.reactivex.Single
import retrofit2.http.*

interface BalanceService {


    @GET("get_balance/")
    fun getBalance(@Query("api_key")api_key:String): Single<Balance>


    @POST("withdraw/")
    @FormUrlEncoded
    fun withDrawCoin(@Field("api_key") api_key:String,
                     @Field("amounts") amount:String,
                    @Field("to_address") to_address:String): Single<Balance>


}