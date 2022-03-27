package com.example.teststupido.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity {
    @PrimaryKey
    var username:String=""
    var password:String?=null
    var porcoDio:Double=0.0
    var idPianeta:Int=0
    var namePianeta:String?=null
}