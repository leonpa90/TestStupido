package com.example.teststupido.di

import android.content.Context
import androidx.room.Room
import com.example.teststupido.data.api.ApiService
import com.example.teststupido.data.api.RetrofitClient
import com.example.teststupido.data.database.BaseData
import com.example.teststupido.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Moduli {
    //faccio una funzione per istanziare il client retofit
   @Provides
    @Singleton
     fun getRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        client.addInterceptor(logging)
        return Retrofit.Builder().client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(RetrofitClient.BASEURL).build()
    }
    @Provides
    @Singleton
    //avviene la magia ovunque
    fun PrividesApi(retrofit: Retrofit):ApiService
    {
     return retrofit.create(ApiService::class.java)
    }
    @Provides
    @Singleton
    //qui abvviene la massima magia perche il context lo prende da non si da dove
    fun getDatabase(@ApplicationContext context: Context): BaseData
    {
        return  Room.databaseBuilder(context, BaseData::class.java,"Prova").fallbackToDestructiveMigration().build()
    }
    fun providesRepository(apiService: ApiService,baseData: BaseData):Repository
    {
       return Repository(apiService,baseData)
    }
}