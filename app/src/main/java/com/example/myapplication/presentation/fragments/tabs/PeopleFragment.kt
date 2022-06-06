package com.example.myapplication.presentation.fragments.tabs

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.presentation.PersonViewModel
import com.example.app.adapters.MyRecyclerAdapter
import com.example.myapplication.domain.models.Person
import com.example.myapplication.domain.models.PersonList
import com.example.myapplication.R
import com.example.myapplication.presentation.adapters.OnItemClickListener
import com.example.myapplication.presentation.fragments.dialog.ProfileDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PeopleFragment : Fragment(), OnItemClickListener {
    private lateinit var mContext: Context
    private var myRecyclerAdapter : MyRecyclerAdapter? = null
    private var myView :View? = null

    private lateinit var personViewModel: PersonViewModel
//    private val personViewModel: PersonViewModel by activityViewModels()

    companion object{
        fun getNewInstance(args: Bundle): Fragment {
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

    override fun onItemClick( person: Person) {
//        childFragmentManager.commit {
//            replace<Person>(R.id.layout.per)
//            setReorderingAllowed(true)
//            addToBackStack("name") // name can be null
//        }
        val bundle = Bundle()
        bundle.putParcelable("person_key", person)
        val dialog = ProfileDialog.getNewInstance(bundle)
        dialog.show(childFragmentManager, "profile_tag")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
//        personViewModel = ViewModelProvider(requireActivity()).get(PersonViewModel::class.java)

        val view = inflater.inflate(R.layout.people_fragment, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_person)

        myRecyclerAdapter = MyRecyclerAdapter(mContext, ArrayList<Person>(), this)
//        myRecyclerAdapter = MyRecyclerAdapter(mContext, ArrayList<Person>())

        recyclerView.adapter = myRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(mContext)

        personViewModel.readAll.observe(viewLifecycleOwner){
            if (it != null)
                if (it.isNotEmpty())
                    myRecyclerAdapter!!.setData(ArrayList(it))
        }

        val swipeContainer = view.findViewById<SwipeRefreshLayout>(R.id.swipe_container)
        swipeContainer.setOnRefreshListener {
//            personViewModel.getPeopleApi(view)
            swipeContainer.setRefreshing(false)
        }

//        personViewModel.getPeopleApi(view)

        myView = view
        return view
    }

//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        personViewModel.getPeopleApi(myView!!)
//    }
}