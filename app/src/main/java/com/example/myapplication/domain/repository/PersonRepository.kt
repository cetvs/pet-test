package com.example.myapplication.domain.repository

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.models.Person

interface PersonRepository {
    fun getPeopleApi(liveData: MutableLiveData<List<Person>>, view: View)
    fun getSortByAlphabet(liveData: MutableLiveData<List<Person>>)
    fun getSortByBirthday(liveData: MutableLiveData<List<Person>>)
    fun deleteAll()
    fun searchPeople(str: String, liveData: MutableLiveData<List<Person>>)
}