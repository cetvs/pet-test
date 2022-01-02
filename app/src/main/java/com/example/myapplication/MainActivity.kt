package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.app.adapters.MyRecyclerAdapter
import com.example.app.api.Constants
import com.example.app.api.SimpleApi
import com.example.app.classes.Person
import com.example.app.classes.PersonList
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
//    lateinit var simpleApi: SimpleApi
//    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        val navController = navHostFragment.navController
        navHostFragment.onStart()
//
//        var swipeContainer = findViewById<SwipeRefreshLayout>(R.id.swipe_container)
//
//        recyclerView = findViewById(R.id.rv_person)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        var myRecyclerAdapter = MyRecyclerAdapter(this, ArrayList<Person>())
//        recyclerView.adapter = myRecyclerAdapter
//
//        swipeContainer.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
//            swipeContainer.setRefreshing(false)
//        })
//
//
//        val request = Request.Builder()
//            .url("https://stoplight.io/mocks/kode-education/trainee-test/25143926/users")
//            .get()
//            .addHeader("Content-Type", "application/json")
//            .build()
//
//        var retrofit : Retrofit = Retrofit.Builder().baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//        simpleApi = retrofit.create(SimpleApi::class.java)
//
//        var call = simpleApi.getPersons()
//
//        call.enqueue(object : Callback<PersonList> {
//            override fun onFailure(call: Call<PersonList>, t: Throwable) {
//
//            }
//
//            override fun onResponse(call: Call<PersonList>, response: Response<PersonList>) {
//                var movies = response.body()!!.items
//                myRecyclerAdapter.setData(movies!!)
//
//            }
//        })
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//
//        val search = menu?.findItem(R.id.menu_search)
//        val searchView = search?.actionView as? SearchView
//        searchView?.isSubmitButtonEnabled = true
//        return super.onCreateOptionsMenu(menu)
//    }

}