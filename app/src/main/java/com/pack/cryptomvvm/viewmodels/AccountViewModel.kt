package com.pack.cryptomvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pack.cryptomvvm.Repository.WithdrawCoinRepo
import com.pack.cryptomvvm.model.Balance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val withdrawCoinRepo: WithdrawCoinRepo): ViewModel( ) {

    private val disposable=CompositeDisposable()
    private val withDrawCoinRepoLiveData= MutableLiveData<Balance>()
    private val isLoading= MutableLiveData<Boolean>()
    private val errorDisplay= MutableLiveData<String>()


    fun getWithdrawCoinRepoLiveData(api_key:String,amount:String,to_address:String): MutableLiveData<Balance>{

        loadData(api_key,amount,to_address)
        return withDrawCoinRepoLiveData

    }

    fun loadData(api_key:String,amount:String,to_address:String){

        disposable.add(withdrawCoinRepo.getWithdrawCoinRepo
            (api_key,
            amount,
            to_address).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({data->

            isLoading.value=false
            getWithdrawCoinRepoLiveData(api_key,amount,to_address).value=data

            },{ error->

            isLoading.value=false

        }))

    }

    fun isLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun errorDisplay(): LiveData<String> {
        return errorDisplay
    }

}