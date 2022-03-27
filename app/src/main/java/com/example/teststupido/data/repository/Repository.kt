package com.example.teststupido.data.repository

import android.content.Context
import com.example.teststupido.data.api.ApiService
import com.example.teststupido.data.api.RetrofitClient
import com.example.teststupido.data.database.BaseData
import com.example.teststupido.model.Pianeti.Pianeta
import com.example.teststupido.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class Repository(context: Context) {
    companion object{
        //singleton
        var repository:Repository? =null
        fun getIstance(context: Context):Repository?{
            if(repository==null){
                repository = Repository(context)
            }
            return repository
        }
    }
    val baseData:BaseData = BaseData.getDatabase(context)
    val apiData:ApiService=RetrofitClient.apiServices

    suspend fun login(username:String, password:String):UserEntity?
    {
        return withContext(Dispatchers.IO) {

            baseData.userDao().getUtente(username,password)

            }


    }
    suspend fun aggiungiDanaro(username:String,denaro:Double):UserEntity?
    {
        return withContext(Dispatchers.IO) {


            val utente = baseData.userDao().getUtenteUser(username)
            utente?.let {
                it.porcoDio = denaro
                baseData.userDao().update(it)
            }
            utente
        }
    }

    fun getDati(username: String): Flow<UserEntity>
    {
        return baseData.userDao().getUtenteFlow(username)
    }
    suspend fun registrati(username: String,password: String):Boolean {
        return withContext(Dispatchers.IO) {
            if(baseData.userDao().getUtenteUser(username)==null) {
                baseData.userDao().insert(UserEntity().apply {
                    this.password=password
                    this.username=username
                })
                true

            }
            else
                false
        }


    }
    fun getPianeta(id:String,userid:String):Flow<Pianeta>
    {

        return flow {
            val pianeta= baseData.userDao().getPianeta(id.toInt())
            if(!pianeta.isNullOrEmpty())
            {
                emit(Pianeta(pianeta))
                return@flow
            }
            val p = apiData.getPianeti(id)
            if(pianeta.isNullOrEmpty())
            {
                baseData.userDao().getUtenteUser(userid)?.let {
                    baseData.userDao().update(it.apply {
                        idPianeta=id.toInt()
                        namePianeta=p.name
                    })
                }

            }


            emit(p)
        }.flowOn(Dispatchers.IO)
    }

}