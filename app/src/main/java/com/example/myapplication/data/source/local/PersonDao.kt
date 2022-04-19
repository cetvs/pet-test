package com.example.myapplication.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.domain.models.Person

//import androidx.room.Dao
//import androidx.room.Delete
//import androidx.room.Insert
//import androidx.room.Query


@Dao
interface PersonDao {
//    @Query("SELECT * FROM person WHERE person.firstName LIKE  :searchQuery")
//    fun getSearchResults(searchQuery : String) : LiveData<List<Person>>

    @Query("SELECT * FROM person ORDER BY  firstName ")
    fun getSort(): List<Person>

    @Query("SELECT * FROM person WHERE firstName LIKE :searchQuery")
    fun search(searchQuery : String): List<Person>


    //    @Query("SELECT * FROM movie")
//    fun getAll(): LiveData<List<Movie>>
//
    @Insert
    fun insertPerson(person: Person)
//
//    @Query("DELETE FROM movie")
//    fun deleteAll()
//
//    @Query("DELETE FROM movie WHERE movie.id = :id")
//    fun delete(id : Int)
//
//    @Query("SELECT * FROM movie WHERE movie.id = :id")
//    fun findMovie(id : Int): Movie? //: List<Movie>
//
//    @Query("SELECT CASE movie.id\n" +
//            "    WHEN :idQuery THEN 1\n" +
//            "    else 0\n" +
//            "END\n" +
//            "FROM movie")
//    fun IsfindMovie(idQuery : Int): Boolean
}