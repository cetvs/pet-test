package com.example.app.room

import android.content.Context
//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase
import com.example.app.classes.Person


//@Database(entities = arrayOf(Person::class), version = 1)
abstract class AppDatabase //: RoomDatabase(){
{
//    abstract fun movieDao(): MovieDao
//
//    companion object{
//        @Volatile
//        private  var INSTANCE: AppDatabase? = null
//
//        fun  getDatabase(context: Context): AppDatabase{
//            val tempInstance = INSTANCE
//            if(tempInstance != null){
//                return  tempInstance
//            }
//
//            synchronized(this){
//                val instance = Room.databaseBuilder(
//                        context.applicationContext,
//                        AppDatabase::class.java,
//                        "user_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//
//    }
}