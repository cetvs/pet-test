package com.example.myapplication.fragments.tabs

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.app.adapters.MyPagerAdapter
import com.example.app.adapters.MyRecyclerAdapter
import com.example.app.api.Constants
import com.example.app.api.RetrofitInstance
import com.example.app.api.SimpleApi
import com.example.app.classes.Person
import com.example.app.classes.PersonList
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class PeopleFragment : Fragment() {
    private lateinit var mContext: Context
    private var myRecyclerAdapter : MyRecyclerAdapter? = null;

    companion object{
        fun getNewInstance(args: Bundle): PeopleFragment {
            val peopleFragment = PeopleFragment()
            peopleFragment.arguments = args
            return peopleFragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var retrofit : Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
//                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//        simpleApi = retrofit.create(SimpleApi::class.java)
    }

    private fun asynchCall(call: Call<PersonList>, view: View) {
        call.enqueue(object : Callback<PersonList> {
            override fun onFailure(call: Call<PersonList>, t: Throwable) {
                Navigation.findNavController(view).navigate(R.id.navigateToOutSearchFragment)
            }

            override fun onResponse(call: Call<PersonList>, response: Response<PersonList>) {
                var movies = response.body()!!.items
                myRecyclerAdapter!!.setData(movies!!)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.people_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_person)

        myRecyclerAdapter =  arguments?.getSerializable("recyclerAdapter") as MyRecyclerAdapter?
//        myRecyclerAdapter = MyRecyclerAdapter(mContext, ArrayList<Person>())

        recyclerView.adapter = myRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(mContext)


        //enqueue call
//        val call = simpleApi.getPersons()
//        asynchCall(call, view)

        //rx
        RetrofitInstance.simpleApi.getPersonsRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getSingle(view))

//        simpleApi.getPersonsRx()
//                .toObservable()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(getObserver(view))


        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipeContainer.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            //enqueue call
//            val callLoc = simpleApi.getPersons()
//            asynchCall(callLoc, view)
            //rx
            RetrofitInstance.simpleApi.getPersonsRx()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getSingle(view))

//            simpleApi.getPersonsRx()
//                    .toObservable()
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(getObserver(view))

            swipeContainer.setRefreshing(false)
        })

        return view
    }

    private fun getObserver(view: View): Observer<PersonList> {
        return object : Observer<PersonList> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: $")
            }

            override fun onNext(personList: PersonList) {
                var movies = personList.items
                myRecyclerAdapter!!.setData(movies!!)
            }

            override fun onError(e: Throwable) {
                Navigation.findNavController(view).navigate(R.id.navigateToOutSearchFragment)
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
        }
    }

    private fun getSingle(view: View): SingleObserver<PersonList> {
        return object : SingleObserver<PersonList> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: $")
            }

            override fun onError(e: Throwable) {
                Navigation.findNavController(view).navigate(R.id.navigateToOutSearchFragment)
            }

            override fun onSuccess(personList: PersonList) {
                var movies = personList.items
                myRecyclerAdapter!!.setData(movies!!)
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        val search = menu?.findItem(R.id.menu_search)
//        val searchView = search?.actionView as? SearchView
//        searchView?.isSubmitButtonEnabled = true
//    }



}