package com.example.myapplication.fragments

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
import com.example.app.api.SimpleApi
import com.example.app.classes.Person
import com.example.app.classes.PersonList
import com.example.myapplication.R
import com.example.myapplication.fragments.tabs.AnalystsFragment
import com.example.myapplication.fragments.tabs.DesignersFragment
import com.example.myapplication.fragments.tabs.PeopleFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MainFragment : Fragment() {
    private lateinit var mContext: Context

    private lateinit var viewPager2: ViewPager2

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.main_fragment, container, false)

        viewPager2 = view.findViewById(R.id.view_pager2)
        val tabs: TabLayout = view.findViewById<TabLayout>(R.id.fragment_tabs)

        val fragmentManager = childFragmentManager
        val adapter = MyPagerAdapter(fragmentManager,lifecycle)

        //create recycler bundle
        val  myRecyclerAdapter = MyRecyclerAdapter(mContext, ArrayList<Person>())
        var bundle = Bundle()
        bundle.putSerializable("recyclerAdapter", myRecyclerAdapter)


        adapter.addTab(PeopleFragment.getNewInstance(bundle), "Все")
        adapter.addTab(DesignersFragment.getNewInstance(bundle), "Designers")
        adapter.addTab(AnalystsFragment.getNewInstance(bundle), "Analysts")

        adapter.notifyDataSetChanged()

        viewPager2.adapter = adapter

        TabLayoutMediator(tabs, viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
            viewPager2.setCurrentItem(tab.position, true)
        }.attach()

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
    }


}