package com.example.teststupido.view

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teststupido.data.repository.Repository
import com.example.teststupido.model.UserEntity
import kotlinx.coroutines.launch

class LogonViewModel:ViewModel() {
    fun login(context: Context,username:String, password:String)

    {
        viewModelScope.launch {
       val user= Repository.getIstance(context)?.login(username,password)
            if(user!=null)
            {
                userData.value=user!!
            }
            else
                errorData.value="Utente mon esiste"

    }

}
    fun registrati(context: Context,username: String,password: String)
    {
        viewModelScope.launch {
            if(!username.isNullOrEmpty()&&!password.isNullOrEmpty()) {
                Repository.getIstance(context)?.registrati(username, password)?.let {
                    if (it) {
                        registrationData.value = "Registrazione avvenuta"
                    } else
                        errorData.value = "Utente esistente gia"
                }
            }
            else
                errorData.value="Campo mancante"
        }
    }
    val userData=MutableLiveData<UserEntity>()
    val errorData=MutableLiveData<String>()
    val registrationData=MutableLiveData<String>()

}