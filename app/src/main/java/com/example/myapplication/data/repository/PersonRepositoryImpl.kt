package com.example.myapplication.data.repository


import android.content.ContentValues
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.data.source.local.PersonDao
import com.example.myapplication.data.source.remote.RetrofitInstance
import com.example.myapplication.domain.models.Person
import com.example.myapplication.domain.models.PersonList
import com.example.myapplication.domain.repository.PersonRepository
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PersonRepositoryImpl(private val localRepository: PersonDao) : PersonRepository {

    private fun getSingle(
        liveData: MutableLiveData<List<Person>>,
        view: View
    ): SingleObserver<PersonList> {
        return object : SingleObserver<PersonList> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
                Navigation.findNavController(view).navigate(R.id.navigateToOutSearchFragment)
            }

            override fun onSuccess(personList: PersonList) {
                val res = personList.items!!
                liveData.postValue(res)
                for (el in res)
                    localRepository.insertPerson(el)
            }
        }
    }

    private fun rxAsynch(liveData: MutableLiveData<List<Person>>, view: View) {
        RetrofitInstance.simpleApi.getPeopleRx()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe(getSingle(liveData, view))
    }

    private fun saveLocalData(res: ArrayList<Person>) {
        for (el in res)
            localRepository.insertPerson(el)
    }

    override fun getPeopleApi(liveData: MutableLiveData<List<Person>>, view: View) {
        rxAsynch(liveData, view)
    }

    override fun getSortByAlphabet(liveData: MutableLiveData<List<Person>>) {
        liveData.postValue(localRepository.getSortByAlphabet())
    }

    override fun getSortByBirthday(liveData: MutableLiveData<List<Person>>) {
        liveData.postValue(localRepository.getSortByBirthday())
    }

    override fun searchPeople(str: String, liveData: MutableLiveData<List<Person>>) {
        liveData.postValue(localRepository.search(str))
    }

    override fun deleteAll() {
        localRepository.deleteAll()
    }
}