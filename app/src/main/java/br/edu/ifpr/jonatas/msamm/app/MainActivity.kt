package br.edu.ifpr.jonatas.msamm.app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.edu.ifpr.jonatas.msamm.R
import br.edu.ifpr.jonatas.msamm.entities.LoginData
import br.edu.ifpr.jonatas.msamm.entities.LoginMessage
import br.edu.ifpr.jonatas.msamm.network.LoginService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var retrofit : Retrofit
    lateinit var service : LoginService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofitSetup()

        btLogon.setOnClickListener {
            val loginData = LoginData(tfEmail.text.toString(), tfPassword.text.toString())
            service.login(loginData).enqueue(object : Callback<LoginMessage> {
                override fun onFailure(call: Call<LoginMessage>, t: Throwable) {
                    TODO()
                }

                override fun onResponse(call: Call<LoginMessage>, response: Response<LoginMessage>) {
                    //Log.e("response", response.message())
                    if(response.body()!!.msg){
                        var intent = Intent(baseContext, IndividuosActivity::class.java)
                        startActivity(intent)
                    }
                }

            })
        }
    }

    private fun retrofitSetup() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://jonatassn.servegame.com/api/samm/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<LoginService>(LoginService::class.java)
    }
}
