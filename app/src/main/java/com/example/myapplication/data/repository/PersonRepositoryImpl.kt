package com.example.myapplication.data.repository


import android.content.ContentValues
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.source.remote.RetrofitInstance
import com.example.myapplication.domain.models.Person
import com.example.myapplication.domain.models.PersonList
import com.example.myapplication.domain.repository.PersonRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PersonRepositoryImpl: PersonRepository {

    private fun getFun(list: ArrayList<Person>, str: String): ArrayList<Person> {
        if(str == "")
            return list

        val res = arrayListOf<Person>()

        for (i in 0 until list.size) {
            if (list[i].position == str) {
                res.add(list[i])
            }
        }
        return res

    }


    private fun getSingle(liveData: MutableLiveData<List<Person>>, view: View,
                          str: String): SingleObserver<PersonList> {
        return object : SingleObserver<PersonList> {
            override fun onSubscribe(d: Disposable) {
                Log.d(ContentValues.TAG, "onSubscribe: $")
            }

            override fun onError(e: Throwable) {
//                Navigation.findNavController(view).navigate(R.id.navigateToOutSearchFragment)
            }

            override fun onSuccess(personList: PersonList) {
                var lst = personList.items
                val res = getFun(lst!!, str)
//                val res = lst!!
                liveData.setValue(res)
            }
        }
    }

    private fun rxAsynch(liveData: MutableLiveData<List<Person>>, view: View, str: String = "") {
//        liveData.value = null
        RetrofitInstance.simpleApi.getPeopleRx()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getSingle(liveData, view, str))
    }

    override fun getPeopleApi(liveData: MutableLiveData<List<Person>>, view: View) {
        rxAsynch(liveData,view)
    }

//    fun getDesignersApi(liveData: MutableLiveData<List<Person>>, view: View) {
//        rxAsynch(liveData,view, "Designer")
//    }
//
//    fun getAnalystsApi(liveData: MutableLiveData<List<Person>>, view: View) {
//        rxAsynch(liveData,view, "Analyst")
//    }
}