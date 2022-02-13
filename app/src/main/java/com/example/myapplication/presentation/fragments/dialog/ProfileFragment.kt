package com.example.myapplication.presentation.fragments.dialog

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.myapplication.presentation.PersonViewModel
import com.example.app.adapters.MyRecyclerAdapter
import com.example.myapplication.data.source.remote.SimpleApi
import com.example.myapplication.domain.models.Person
import com.example.myapplication.R


class ProfileFragment : DialogFragment() {
    private lateinit var mContext: Context
    private lateinit var simpleApi: SimpleApi
    private var myRecyclerAdapter: MyRecyclerAdapter? = null

    private lateinit var personViewModel: PersonViewModel
    private var myView :View? = null

    companion object {
        fun getNewInstance(args: Bundle): ProfileFragment {
            val profileFragment = ProfileFragment()
//            profileFragment.arguments?.putParcelable("person", args)
            profileFragment.arguments = args
            return profileFragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun personToView(person: Person, view: View){
        var name = view.findViewById<TextView>(R.id.tv_name)
        name.setText(person.firstName + " " + person.lastName)
        var description = view.findViewById<TextView>(R.id.tv_position)
        description.setText(person.position)

        var phone = view.findViewById<TextView>(R.id.tv_phone)
        phone.setText("+7 " + person.phone)
        phone.setOnClickListener{
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8" + person.phone))
            startActivity(intent)
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.person_profile, container, false)

        val person =  arguments?.getParcelable("person_key") as Person?
        personToView(person!!, view)

//        val button = view.findViewById<Button>(R.id.btn_back)
//        button.setOnClickListener{
//            this.onCancel(this)
//        }

        return view
    }

//    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
//        val inflater = super.onGetLayoutInflater(savedInstanceState)
//        val contextThemeWrapper: Context = ContextThemeWrapper(requireContext(), R.style.DialogTheme)
//        return inflater.cloneInContext(contextThemeWrapper)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

//    override fun onStart() {
//        super.onStart()
//        dialog?.let {
//            val width = ViewGroup.LayoutParams.MATCH_PARENT
//            val height = ViewGroup.LayoutParams.MATCH_PARENT
//            it.window?.setLayout(width, height)
//        }
//    }



}