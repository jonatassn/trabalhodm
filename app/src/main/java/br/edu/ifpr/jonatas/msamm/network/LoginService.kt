package br.edu.ifpr.jonatas.msamm.network

import br.edu.ifpr.jonatas.msamm.entities.LoginData
import br.edu.ifpr.jonatas.msamm.entities.LoginMessage
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @Headers("Accept: application/json")
    @POST("user/auth")
    fun login( @Body body : LoginData) : Call<LoginMessage>
}