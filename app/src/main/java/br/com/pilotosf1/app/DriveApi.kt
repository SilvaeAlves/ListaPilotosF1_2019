package br.com.pilotosf1.app

import retrofit2.Call
import retrofit2.http.GET



interface DriveApi {

    @GET("/api/f1/2019/drivers.json")
    fun getListaPilotos(

    ): Call<GetDriveResponse>


}