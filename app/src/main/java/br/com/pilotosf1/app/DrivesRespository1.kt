package br.com.pilotosf1.app

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DrivesRespository {

    private val api:DriveApi

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
        client.addInterceptor(interceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://ergast.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        api = retrofit.create(DriveApi::class.java)
    }

    fun getListPilots(
        onSuccess: (driver: List<Driver>) -> Unit,
        onError: () -> Unit
    ) {
        api.getListaPilotos()
            .enqueue(object : Callback<GetDriveResponse> {
                override fun onResponse(
                    call: Call<GetDriveResponse>,
                    response: Response<GetDriveResponse>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            onSuccess.invoke(responseBody.data.driverTable.drivers)
                        } else {
                            onError.invoke()
                        }
                    } else {
                        onError.invoke()
                    }
                }

                override fun onFailure(call: Call<GetDriveResponse>, t: Throwable) {
                    onError.invoke()
                }
            })
    }
}