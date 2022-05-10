package com.example.myapplication.presentation.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.presentation.makeToast
import com.example.myapplication.presentation.replaceFragment
import com.example.myapplication.presentation.utils.Auth
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class EnterPhoneFragment : Fragment() {
    private lateinit var callBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.enter_phone_fragment, container, false)
        return view
    }

//    fun createCallbeck(): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
//        return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//            override fun onVerificationCompleted(сredential: PhoneAuthCredential) {
//                Auth.signInWithCredential(сredential).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        makeToast(mContext, "удачно")
//                    } else makeToast(context!!, it.exception?.message.toString())
//                }
//            }
//
//            override fun onVerificationFailed(p0: FirebaseException) {
//                makeToast(mContext, "Не ввел телефон")
//            }
//
//            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
//                super.onCodeSent(id, token)
//                replaceFragment(parentFragmentManager, EnterCodeFragment(), true)
//                makeToast(mContext, "поменял")
//            }
//        }
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onStart() {
        super.onStart()
        val btn = view?.findViewById<FloatingActionButton>(R.id.next_code_fragment_btn)
        btn?.setOnClickListener {
            callBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(сredential: PhoneAuthCredential) {
                    Auth.signInWithCredential(сredential).addOnCompleteListener {
                        if (it.isSuccessful) {
                            makeToast(mContext, "удачно")
                        } else makeToast(context!!, it.exception?.message.toString())
                    }
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    makeToast(mContext, "Не ввел телефон")
                }

                override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(id, token)
                    replaceFragment(parentFragmentManager, EnterCodeFragment(), true)
                    makeToast(mContext, "поменял")
                }
            }

            val inputPhone = view?.findViewById<EditText>(R.id.input_phone)
            val phoneNumber = inputPhone?.text.toString()

            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,
                60,
                TimeUnit.SECONDS,
                activity as MainActivity,
                callBack
            )
        }
    }
}