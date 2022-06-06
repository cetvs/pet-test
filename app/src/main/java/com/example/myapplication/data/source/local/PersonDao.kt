package com.example.myapplication.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.domain.models.Person

//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.Query


@Dao
interface PersonDao {
    @Query("SELECT * FROM person ORDER BY firstName ")
    fun getSortByAlphabet(): List<Person>

    @Query("SELECT * FROM person ORDER BY birthday ")
    fun getSortByBirthday(): List<Person>?

    @Query("SELECT * FROM person WHERE firstName LIKE '%' ||:searchQuery || '%'")
    fun search(searchQuery : String): List<Person>

    @Insert
    fun insertPerson(person: Person)

    @Query("DELETE FROM person")
    fun deleteAll()
}