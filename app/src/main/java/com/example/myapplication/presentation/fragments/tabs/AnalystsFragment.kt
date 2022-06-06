package com.example.myapplication.presentation.fragments.tabs

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.presentation.PersonViewModel
import com.example.app.adapters.MyRecyclerAdapter
import com.example.myapplication.data.source.remote.SimpleApi
import com.example.myapplication.domain.models.Person
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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.analyst_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_person)

        myRecyclerAdapter = MyRecyclerAdapter(mContext, ArrayList<Person>())

        recyclerView.adapter = myRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(mContext)

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

        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipeContainer.setOnRefreshListener{
            personViewModel.getPeopleApi(view)
            swipeContainer.setRefreshing(false)
        }

        myView = view
        return view
    }
}