package com.example.teststupido.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teststupido.data.database.dao.UserDao
import com.example.teststupido.model.UserEntity

@Database(entities = [UserEntity::class], version = 4)
abstract class BaseData:RoomDatabase()
{
    abstract fun userDao():UserDao
   companion object {
       fun getDatabase(context: Context):BaseData
       {
           return  Room.databaseBuilder(context,BaseData::class.java,"Prova").fallbackToDestructiveMigration().build()
       }
   }
}