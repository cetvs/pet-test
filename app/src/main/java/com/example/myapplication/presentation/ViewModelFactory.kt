package com.example.myapplication.presentation

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.data.repository.PersonRepositoryImpl
import com.example.myapplication.data.source.local.AppDatabase
import com.example.myapplication.data.source.local.PersonDao

class ViewModelFactory(private val context: Context)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val personDao = AppDatabase.getDatabase(context).personDao()
        val repositoryImpl = PersonRepositoryImpl(personDao)
        return PersonViewModel(repositoryImpl) as T
    }


}
