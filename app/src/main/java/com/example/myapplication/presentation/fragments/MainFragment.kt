package com.example.myapplication.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.RadioGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.app.adapters.MyPagerAdapter
import com.example.myapplication.R
import com.example.myapplication.domain.models.FirebasePerson
import com.example.myapplication.domain.models.Person
import com.example.myapplication.domain.models.TestType
import com.example.myapplication.presentation.PersonViewModel
import com.example.myapplication.presentation.ViewModelFactory
import com.example.myapplication.presentation.fragments.dialog.SortSheetDialogFragment
import com.example.myapplication.presentation.fragments.tabs.AnalystsFragment
import com.example.myapplication.presentation.fragments.tabs.DesignersFragment
import com.example.myapplication.presentation.fragments.tabs.PeopleFragment
import com.example.myapplication.presentation.makeToast
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class MainFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var mContext: Context
    private lateinit var viewPager2: ViewPager2
    private lateinit var personViewModel: PersonViewModel
    private lateinit var reference : DatabaseReference

    companion object {
        fun getNewInstance(): Fragment {
            val peopleFragment = PeopleFragment()
            return peopleFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        personViewModel =
            ViewModelProvider(requireActivity(), ViewModelFactory(requireActivity())).get(
                PersonViewModel::class.java
            )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        reference = Firebase.database.getReference("message")

        val view = inflater.inflate(R.layout.main_fragment, container, false)

        val listener = RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radio_alphabet -> personViewModel.getSortByAlphabet()
                R.id.radio_birthday -> personViewModel.getSortByBirthday()
            }
        }
        val modalBottomSheet = SortSheetDialogFragment(listener)

        val button = view.findViewById<Button>(R.id.sort_button)
        button.setOnClickListener {
            modalBottomSheet.show(childFragmentManager, SortSheetDialogFragment.TAG)
        }

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                personViewModel.searchPeople(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                personViewModel.searchPeople(query)
                return false
            }
        })

//        val person = FirebasePerson("2","2","2","2","2","2","2","2")
//        addToFirebase(person)
//        val test = TestType("4")
//        reference.setValue("mes")

        viewPagerCreate(view)
        return view
    }

    override fun onStart() {
        super.onStart()
        test()
    }

    fun test(){
//        val database = FirebaseDatabase.getInstance()
        val database = Firebase.database
        val ref = database.getReference("People")
        ref.push().setValue("423")
    }

    fun addToFirebase(person: FirebasePerson){
//        val database = Firebase.database
//        val ref = database.getReference("People")
        reference.push().setValue(person)
    }

    fun getFromDatabese(){
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for(ds in dataSnapshot.children)
                    ds.getValue<Person>()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                makeToast("firebase error")
            }
        }

        reference.addValueEventListener(postListener)
    }

//    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        val search = menu.findItem(R.id.menu_search)
//        val searchView = search?.actionView as? SearchView
//        searchView?.setOnQueryTextListener(this)
//        searchView?.isSubmitButtonEnabled = true
//
//        val menuSort = menu.findItem(R.id.menu_sort)
//        menuSort?.setOnMenuItemClickListener {
//            personViewModel.getSortPeople()
//
//            return true
//        }
//
//        menuSort?.onOptionsItemSelected {
//            personViewModel.getSortPeople()
////            Log.v("hy","4")
//        }
//
//    }

    fun viewPagerCreate(view: View){
        viewPager2 = view.findViewById(R.id.view_pager2)!!
        val tabs = view.findViewById<TabLayout>(R.id.fragment_tabs)!!
        val adapter = MyPagerAdapter(childFragmentManager, lifecycle)

        adapter.addTab(PeopleFragment(), "Все")
        adapter.addTab(DesignersFragment(), "Designers")
        adapter.addTab(AnalystsFragment(), "Analysts")

        viewPager2.adapter = adapter

        TabLayoutMediator(tabs, viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
            viewPager2.setCurrentItem(tab.position, true)
        }.attach()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        personViewModel.readAll.observe(viewLifecycleOwner, Observer {
        })
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        return true
    }


}