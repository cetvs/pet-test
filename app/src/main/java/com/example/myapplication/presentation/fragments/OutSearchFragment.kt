package com.example.app.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.myapplication.R

class OutSearchFragment : Fragment() {
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.out_search, container, false)
        val textView = view.findViewById<TextView>(R.id.textViewRequest)
        textView.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.navigateToMainFragment)
        }
        return view
    }

}