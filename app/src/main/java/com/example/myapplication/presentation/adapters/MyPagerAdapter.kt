package com.example.app.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle) :  FragmentStateAdapter(manager,lifecycle ){
    private val fragmentList : MutableList<Fragment> = ArrayList()
    private val titleList : MutableList<String> = ArrayList()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }


    fun addTab(fragment: Fragment, title: String){
        fragmentList.add(fragment)
        titleList.add(title)
    }

    fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}
