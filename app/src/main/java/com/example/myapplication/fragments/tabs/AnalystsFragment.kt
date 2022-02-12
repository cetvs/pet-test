package com.example.myapplication.fragments.tabs

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.app.PersonViewModel
import com.example.app.adapters.MyRecyclerAdapter
import com.example.app.api.SimpleApi
import com.example.app.classes.Person
import com.example.app.classes.PersonList
import com.example.myapplication.R



class AnalystsFragment : Fragment() {
    private lateinit var mContext: Context
    private lateinit var simpleApi: SimpleApi
    private var myRecyclerAdapter: MyRecyclerAdapter? = null

    private lateinit var personViewModel: PersonViewModel
    private var myView :View? = null


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
        personViewModel = ViewModelProvider(requireActivity()).get(PersonViewModel::class.java)
//        personViewModel = ViewModelProvider(this).get(PersonViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.analyst_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_person)



//        myRecyclerAdapter = arguments?.getSerializable("recyclerAdapter") as MyRecyclerAdapter?

        myRecyclerAdapter = MyRecyclerAdapter(mContext, ArrayList<Person>())

        recyclerView.adapter = myRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(mContext)

        //rx
//        personViewModel.getFromApi(view)


//        RetrofitInstance.simpleApi.getPeopleRx()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(getSingle(view))
//
//        var live = MutableLiveData<List<Person>>()
//        personViewModel.readAll

        personViewModel.readAll.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val res = ArrayList<Person>()
                for (elem in it)
                    if (elem.position == "Analyst")
                        res.add(elem)

                if (it.isNotEmpty()) {
                    myRecyclerAdapter!!.setData(ArrayList(res))
                }
            }
        })

//        personViewModel.getFromApi().observe(this, nameObserver)
//        personViewModel.getAnalystsApi(view)


        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipeContainer.setOnRefreshListener{
            personViewModel.getPeopleApi(view)
            swipeContainer.setRefreshing(false)
        }

        myView = view
        return view
    }

    override fun onResume() {
        super.onResume()
//        personViewModel.getAnalystsApi(myView!!)
    }
}