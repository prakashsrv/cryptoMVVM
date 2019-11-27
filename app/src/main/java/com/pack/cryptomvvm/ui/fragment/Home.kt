package com.pack.cryptomvvm.ui.fragment


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.pack.cryptomvvm.BaseApplication
import com.pack.cryptomvvm.R
import com.pack.cryptomvvm.viewmodels.HomeViewModels
import com.pack.cryptomvvm.databinding.FragmentHomeBinding
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class Home : Fragment() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var homeViewModels: HomeViewModels
    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        (activity!!.application as BaseApplication).getSharedComponent().inject(this)

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return fragmentHomeBinding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apikey=sharedPreferences.getString("APIKEY","0000")

        homeViewModels= ViewModelProviders.of(this,viewModelFactory).get(HomeViewModels::class.java)

        homeViewModels.isLoading().observe(this, Observer { isLoading ->


            if(isLoading){
                fragmentHomeBinding.progresBar.visibility= View.VISIBLE
            }else{
                fragmentHomeBinding.progresBar.visibility=View.INVISIBLE
            }


        })

        homeViewModels.errorDisplay().observe(this, Observer { error->
            Log.e("Home",error.toString())
        })

        homeViewModels.getBalanceMutableLiveData(apikey).observe(this, Observer { data->
            fragmentHomeBinding.balance=data
        })

    }


}
