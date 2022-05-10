package com.example.myapplication.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.presentation.replaceFragment

class EnterCodeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.enter_code_fragment, container, false)
        val inputPhone = view.findViewById<EditText>(R.id.input_code)
        inputPhone.addTextChangedListener (
            object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    val code = inputPhone.text.toString().length
                    if (code == 4) {
                        parentFragmentManager.popBackStack()
                        replaceFragment(parentFragmentManager, MainFragment())
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

            }
        )
        return view
    }


}