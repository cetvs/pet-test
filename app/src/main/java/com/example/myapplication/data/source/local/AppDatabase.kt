package com.example.myapplication.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.domain.models.Person


//import androidx.room.Database
//import androidx.room.Room
//import androidx.room.RoomDatabase


@Database(entities = arrayOf(Person::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun personDao(): PersonDao

//    companion object {
//        const val DB_NAME = "person_database.db"
//    }

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "person_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}