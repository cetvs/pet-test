package com.example.myapplication.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.presentation.PersonViewModel
import com.example.app.adapters.MyPagerAdapter
import com.example.myapplication.R
import com.example.myapplication.presentation.fragments.tabs.AnalystsFragment
import com.example.myapplication.presentation.fragments.tabs.DesignersFragment
import com.example.myapplication.presentation.fragments.tabs.PeopleFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment(),  SearchView.OnQueryTextListener {
    private lateinit var mContext: Context
    private lateinit var viewPager2: ViewPager2
    private lateinit var personViewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

//        create recycler bundle
//        val  myRecyclerAdapter = MyRecyclerAdapter(mContext, ArrayList<Person>())
//        var bundle = Bundle()
//        bundle.putSerializable("recyclerAdapter", myRecyclerAdapter)
//        adapter.addTab(PeopleFragment.getNewInstance(bundle), "Все")
//        adapter.addTab(DesignersFragment.getNewInstance(bundle), "Designers")
//        adapter.addTab(AnalystsFragment.getNewInstance(bundle), "Analysts")

        //create recycler bundle
//        var bundle = Bundle()
//        bundle.putSerializable("VM", personViewModel)

        adapter.addTab(PeopleFragment(), "Все")
        adapter.addTab(DesignersFragment(), "Designers")
        adapter.addTab(AnalystsFragment(), "Analysts")

//        adapter.notifyDataSetChanged()

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
        searchView?.setOnQueryTextListener(this)
        searchView?.isSubmitButtonEnabled = true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {

        return true
    }



}