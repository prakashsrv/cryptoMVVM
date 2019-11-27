package com.pack.cryptomvvm.Repository

import com.pack.cryptomvvm.model.Balance
import com.pack.cryptomvvm.service.BalanceService
import io.reactivex.Single
import javax.inject.Inject

class WithdrawCoinRepo @Inject constructor(private val balanceService: BalanceService) {


    fun getWithdrawCoinRepo(api_key:String,amount:String,to_address:String):Single<Balance>{
        return balanceService.withDrawCoin(api_key,amount,to_address)
    }

}