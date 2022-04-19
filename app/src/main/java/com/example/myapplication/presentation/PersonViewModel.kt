package com.example.myapplication.presentation

import android.content.Context
import android.view.View
import androidx.lifecycle.*
import com.example.myapplication.domain.models.Person
import com.example.myapplication.data.repository.PersonRepositoryImpl
import com.example.myapplication.data.source.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PersonViewModel
    (val repositoryImpl: PersonRepositoryImpl): ViewModel() {

//    var mutableList = mutableListOf<Person>();
    private val mutableLiveData = MutableLiveData<List<Person>>()
    var readAll: LiveData<List<Person>> = mutableLiveData


//    init {
//
//    }

    fun getPeopleApi(view : View) {
//        viewModelScope.launch(Dispatchers.IO){
            repositoryImpl.getPeopleApi(mutableLiveData,view)
//        }
    }

    fun sortPeople() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.getSortPeople(mutableLiveData);
        }
    }

    fun searchPeople(string: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.searchPeople(string, mutableLiveData);
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

//    fun addPerson(person: Person){
//        viewModelScope.launch(Dispatchers.IO){
//            repositoryImpl.addPerson(person)
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
