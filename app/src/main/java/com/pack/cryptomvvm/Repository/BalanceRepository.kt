package com.pack.cryptomvvm.Repository

import com.pack.cryptomvvm.model.Balance
import com.pack.cryptomvvm.service.BalanceService
import io.reactivex.Single
import javax.inject.Inject

class BalanceRepository @Inject constructor(private val balanceService: BalanceService) {


    fun getBalance_Repo(apikey:String): Single<Balance>{
        return balanceService.getBalance(apikey)
    }


}