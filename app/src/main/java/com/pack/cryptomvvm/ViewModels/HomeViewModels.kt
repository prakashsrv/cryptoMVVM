package com.pack.cryptomvvm.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pack.cryptomvvm.Repository.BalanceRepository
import com.pack.cryptomvvm.model.Balance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModels @Inject constructor(private val balanceRepository: BalanceRepository): ViewModel() {

    private val disposable=CompositeDisposable()
    private val balanceMutableLiveData = MutableLiveData<Balance>()
    private val isLoading=MutableLiveData<Boolean>()
    private val errorDispaly=MutableLiveData<String>()


    fun getBalanceMutableLiveData(api:String):MutableLiveData<Balance> {
        loadData(api)
        return balanceMutableLiveData

    }

    private fun loadData(api: String){

        disposable.add(
            balanceRepository.getBalance_Repo(api).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({ data ->
                    getBalanceMutableLiveData(api).value = data
                    isLoading.value=false
                }, { error ->
                    isLoading.value=false
                    Log.e("HOME", error.toString())
                })
        )
    }

    fun isLoading(): LiveData<Boolean>{
        return isLoading
    }

    fun errorDisplay(): LiveData<String>{
        return errorDispaly
    }


}
