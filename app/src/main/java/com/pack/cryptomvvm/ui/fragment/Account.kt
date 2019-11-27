package com.pack.cryptomvvm.ui.fragment


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pack.cryptomvvm.BaseApplication
import com.pack.cryptomvvm.R
import com.pack.cryptomvvm.databinding.FragmentAccountBinding
import com.pack.cryptomvvm.viewmodels.AccountViewModel
import com.pack.cryptomvvm.viewmodels.ViewModelFactory
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class Account : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var accountViewModel: AccountViewModel

    private lateinit var fragmentAccounBinding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity!!.application as BaseApplication).getSharedComponent().inject(this)

        fragmentAccounBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_account,container,false)

        return fragmentAccounBinding.root

      }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apikey=sharedPreferences.getString("APIKEY","0000")?: " "

        accountViewModel=ViewModelProviders.of(this,viewModelFactory).get(AccountViewModel::class.java)

        accountViewModel.errorDisplay().observe(this, Observer { error->

            fragmentAccounBinding.errorTxt.text=error
        })

        accountViewModel.isLoading().observe(this, Observer { isLoading->

            if(isLoading){
                fragmentAccounBinding.progresBar.visibility=View.VISIBLE
            }else{
                fragmentAccounBinding.progresBar.visibility=View.INVISIBLE
            }
        })

        accountViewModel.getWithdrawCoinRepoLiveData(apikey,"100","asdasdasfaga").observe(this,
            Observer { data->
                Log.e("Account",data.status)
            })
    }


}
