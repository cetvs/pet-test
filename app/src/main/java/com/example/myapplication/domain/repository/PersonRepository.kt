package com.example.myapplication.domain.repository

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.domain.models.Person

interface PersonRepository {
    fun getPeopleApi(liveData: MutableLiveData<List<Person>>, view: View)
    fun getSortPeople(liveData: MutableLiveData<List<Person>>)
    fun searchPeople(str: String, liveData: MutableLiveData<List<Person>>)
}