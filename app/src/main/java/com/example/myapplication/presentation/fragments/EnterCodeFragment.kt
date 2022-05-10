package com.example.myapplication.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.presentation.fragments.dialog.ProfileDialog
import com.example.myapplication.presentation.makeToast
import com.example.myapplication.presentation.replaceFragment
import com.example.myapplication.presentation.utils.Auth
import com.google.firebase.auth.PhoneAuthProvider

class EnterCodeFragment : Fragment() {

    companion object {
        fun getNewInstance(args: Bundle): EnterCodeFragment {
            val enterCodeFragment = EnterCodeFragment()
            enterCodeFragment.arguments = args
            return enterCodeFragment
        }
    }

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
                    val code = inputPhone.text.toString()
                    if (code.length == 6) {
                        val id = arguments?.getString("id")!!
                        enterCode(id, code)
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

    private fun enterCode(id: String,code: String) {
        val credential = PhoneAuthProvider.getCredential(id, code)
        Auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful){
                parentFragmentManager.popBackStack()
                replaceFragment(parentFragmentManager, MainFragment())
            } else makeToast(task.exception?.message.toString())
        }
    }


}