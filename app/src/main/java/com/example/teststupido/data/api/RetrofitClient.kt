package com.example.teststupido.data.api



import com.example.teststupido.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // istanzio la baseurl basata sul flavour settato sul gradle
    val BASEURL = BuildConfig.url

    //faccio una funzione per istanziare il client retofit
    private fun getRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
        return Retrofit.Builder().client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASEURL).build()
    }

    // ho usato by lazy per istanziare solo una volta retorfit
    val apiServices: ApiService by lazy {
        getRetrofit().create(ApiService::class.java)
    }

}