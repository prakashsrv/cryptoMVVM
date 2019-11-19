package com.pack.cryptomvvm.service

import retrofit2.http.GET
import retrofit2.http.Query

interface BalanceService {


    @GET("get_balance/")
    fun getBalance(@Query("api_key")api_key:String)

}