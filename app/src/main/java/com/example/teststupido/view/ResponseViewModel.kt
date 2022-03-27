package com.example.teststupido.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teststupido.data.repository.Repository
import com.example.teststupido.model.Pianeti.Pianeta
import com.example.teststupido.model.UserEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ResponseViewModel:ViewModel()
{
    val data=MutableLiveData<UserEntity>()
    val pianetaData=MutableLiveData<Pianeta>()
    fun getUserInfo(context: Context,username:String)
    {
        viewModelScope.launch {
            Repository.getIstance(context)?.getDati(username)?.catch {  }?.collect {
                    data.value=it
                    getPianeta(context,it.porcoDio.toInt().toString(),username)
            }
        }
    }
    fun aggiungiDanaro(context: Context,username: String,danaro:Double)
    {
        viewModelScope.launch {
            Repository.getIstance(context)?.aggiungiDanaro(username,danaro)?.let {
//
            }
        }
    }
    fun getPianeta(context: Context,id:String,username: String)
    {
        if(!id.isNullOrEmpty()&&id!="0")
        viewModelScope.launch { Repository.getIstance(context)?.getPianeta(id,username)?.collect{
            pianetaData.value=it

        } }
    }

    }
