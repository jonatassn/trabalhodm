package br.com.jonatassn.mobilesamm.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import br.com.jonatassn.mobilesamm.R
import br.com.jonatassn.mobilesamm.entities.LoginData
import br.com.jonatassn.mobilesamm.entities.LoginMessage
import br.com.jonatassn.mobilesamm.network.LoginService
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginFragment : Fragment() {
    lateinit var retrofit : Retrofit
    lateinit var service : LoginService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var viewInflada = inflater.inflate(R.layout.fragment_login, container, false)

        viewInflada.btLogin.setOnClickListener {
            val loginData = LoginData(tfLogin.text.toString(), tfPassword.text.toString())
            findNavController().navigate(R.id.action_loginFragment_to_especimens)
//            service.login(loginData).enqueue(object : Callback<LoginMessage> {
//                override fun onFailure(call: Call<LoginMessage>, t: Throwable) {
//                    TODO()
//                }
//
//                override fun onResponse(call: Call<LoginMessage>, response: Response<LoginMessage>) {
//                    //Log.e("response", response.message())
//                    if(response.body()!!.msg){
//
//                    }
//                }
//
//            })


        }

        return viewInflada

    }

    private fun retrofitSetup() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://jonatassn.servegame.com/api/samm/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<LoginService>(LoginService::class.java)
    }

}