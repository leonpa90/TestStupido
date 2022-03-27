package com.example.teststupido.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.teststupido.model.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao
{
    @Query("select * from UserEntity where username=:username and password=:password")
    fun getUtente(username:String,password:String):UserEntity?
    @Query("select * from UserEntity where username=:username")
    fun getUtenteFlow(username:String):Flow<UserEntity>
    @Query("select * from UserEntity where username=:username")
    fun getUtenteUser(username:String):UserEntity?
    @Insert
    fun insert(userEntity: UserEntity)
    @Update
    fun update(userEntity: UserEntity)
    @Query("select namePianeta from UserEntity where idPianeta=:id")
    fun getPianeta(id:Int):String

}