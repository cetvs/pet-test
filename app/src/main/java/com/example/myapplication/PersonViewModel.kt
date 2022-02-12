package com.example.app

import android.app.Application

import android.view.View
import androidx.lifecycle.*
import com.example.app.classes.Person
import com.example.myapplication.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PersonViewModel(): ViewModel() {

    private val repository: PersonRepository = PersonRepository()
//    var mutableList = mutableListOf<Person>();
    private val mutableLiveData = MutableLiveData<List<Person>>()
    var readAll: LiveData<List<Person>> = mutableLiveData


//    init {
//
//    }

    fun getPeopleApi(view : View) {
        viewModelScope.launch(Dispatchers.IO){
            repository.getPeopleApi(mutableLiveData,view)
        }
    }

//    fun getDesignersApi(view : View) {
//        viewModelScope.launch(Dispatchers.IO){
//            repository.getDesignersApi(mutableLiveData,view)
//        }
//    }
//
//    fun getAnalystsApi(view : View) {
//        viewModelScope.launch(Dispatchers.IO){
//            repository.getAnalystsApi(mutableLiveData,view)
//        }
//    }

//    fun addPerson(movie: Person){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.addMovie(movie)
//        }
//    }

    fun addPeople(lst: List<Person>){

    }

//    fun makeSearch(string: String) {
//        viewModelScope.launch{
//            repository.makeSearch(_movies, string)
//        }
//    }
//
//    fun getPopular() {
//        viewModelScope.launch(Dispatchers.IO){
//            repository.getPopular(_movies)
//        }
//    }
//
//    fun addMovie(movie: Person){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.addMovie(movie)
//        }
//    }
//
//    fun addMovies(lst: List<Person>){
//        viewModelScope.launch(Dispatchers.IO){
//            mutableLiveData.
//        }
//    }
//
//    fun deleteMovie(id: Int){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.deleteMovie(id)
//        }
//    }
//
//    fun deleteMovies(){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.deleteMovies()
//        }
//    }

}
