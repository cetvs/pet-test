package com.example.myapplication.data.repository


import android.content.ContentValues
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.myapplication.R
import com.example.myapplication.data.source.local.PersonDao
import com.example.myapplication.data.source.remote.RetrofitInstance
import com.example.myapplication.domain.models.Person
import com.example.myapplication.domain.models.PersonList
import com.example.myapplication.domain.repository.PersonRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PersonRepositoryImpl(private val localRepository: PersonDao): PersonRepository {


//    private fun getFun(list: ArrayList<Person>, str: String): ArrayList<Person> {
//        if(str == "")
//            return list
//
//        val res = arrayListOf<Person>()
//
//        for (i in 0 until list.size) {
//            if (list[i].position == str) {
//                res.add(list[i])
//            }
//        }
//        return res
//
//    }


    private fun getSingle(liveData: MutableLiveData<List<Person>>, view: View,
                          str: String): SingleObserver<PersonList> {
        return object : SingleObserver<PersonList> {
            override fun onSubscribe(d: Disposable) {
                Log.d(ContentValues.TAG, "onSubscribe: $")
            }

            override fun onError(e: Throwable) {
                Navigation.findNavController(view).navigate(R.id.navigateToOutSearchFragment)
            }

            override fun onSuccess(personList: PersonList) {
                var lst = personList.items
//                val res = getFun(lst!!, str)
                val res = lst!!
                liveData.postValue(res)

                for(el in res)
                    localRepository.insertPerson(el)
//                saveLocalData(res)
            }
        }
    }

    private fun rxAsynch(liveData: MutableLiveData<List<Person>>, view: View, str: String = "") {
//        liveData.value = null
        RetrofitInstance.simpleApi.getPeopleRx()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe(getSingle(liveData, view, str))

    }

    private fun saveLocalData(res : ArrayList<Person>){

        for(el in res)
            localRepository.insertPerson(el)

    }

    override fun getPeopleApi(liveData: MutableLiveData<List<Person>>, view: View) {
        rxAsynch(liveData,view)

//        val observer = Observable.just(liveData.value!!)
//            .subscribeOn(Schedulers.io())
//            .subscribe {
//                for(el in it)
//                    localRepository.insertPerson(el) }

//        liveData.observe(, Observer )
//        saveLocalData(liveData)
    }

    override fun getSortPeople(liveData: MutableLiveData<List<Person>>){
//        liveData.value = localRepository.getSort()
        liveData.postValue(localRepository.getSort())
    }

    override fun searchPeople(str: String, liveData: MutableLiveData<List<Person>>){
//        liveData.value = localRepository.getSort()
        liveData.postValue(localRepository.search(str))
    }



//    fun getDesignersApi(liveData: MutableLiveData<List<Person>>, view: View) {
//        rxAsynch(liveData,view, "Designer")
//    }
//
//    fun getAnalystsApi(liveData: MutableLiveData<List<Person>>, view: View) {
//        rxAsynch(liveData,view, "Analyst")
//    }
}