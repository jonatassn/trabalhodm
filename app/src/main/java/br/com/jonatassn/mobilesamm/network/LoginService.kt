package br.com.jonatassn.mobilesamm.network

import br.com.jonatassn.mobilesamm.entities.LoginData
import br.com.jonatassn.mobilesamm.entities.LoginMessage
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @Headers("Accept: application/json")
    @POST("user/auth")
    fun login( @Body body : LoginData) : Call<LoginMessage>
}