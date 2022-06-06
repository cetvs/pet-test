package com.example.myapplication.presentation.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.presentation.utils.replaceLoginFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RegistrationNameFragment : Fragment() {

    companion object {
        fun getNewInstance(args: Bundle): RegistrationNameFragment {
            val enterCodeFragment = RegistrationNameFragment()
            enterCodeFragment.arguments = args
            return enterCodeFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.registration_name_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        val btn = view?.findViewById<FloatingActionButton>(R.id.add_person_to_firebase_btn)
        btn?.setOnClickListener {
            replaceLoginFragment(
                parentFragmentManager, DepartmentInfoFragment.getNewInstance(makeBundle())
            )
        }
    }

    private fun makeBundle(): Bundle {
        val name = view?.findViewById<EditText>(R.id.input_department)
        val surname = view?.findViewById<EditText>(R.id.input_position)
        val tag = view?.findViewById<EditText>(R.id.input_position)
        val bundle = Bundle(arguments)
        bundle.putString("name", name?.text.toString())
        bundle.putString("surname", surname?.text.toString())
        bundle.putString("tag", tag?.text.toString())
        return bundle
    }
}