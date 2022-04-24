package com.example.myapplication.presentation

import android.view.View
import androidx.lifecycle.*
import com.example.myapplication.domain.models.Person
import com.example.myapplication.data.repository.PersonRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PersonViewModel(val repositoryImpl: PersonRepositoryImpl): ViewModel() {
    private val mutableLiveData = MutableLiveData<List<Person>>()
    var readAll: LiveData<List<Person>> = mutableLiveData

    fun getPeopleApi(view : View) {
//        viewModelScope.launch(Dispatchers.IO){
            deleteAll()
            repositoryImpl.getPeopleApi(mutableLiveData, view)
//        }
    }

    fun getSortByAlphabet() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.getSortByAlphabet(mutableLiveData)
        }
    }

    fun getSortByBirthday() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.getSortByBirthday(mutableLiveData)
        }
    }

    fun searchPeople(string: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.searchPeople(string, mutableLiveData)
        }
    }

    private fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryImpl.deleteAll()
        }
    }
}
