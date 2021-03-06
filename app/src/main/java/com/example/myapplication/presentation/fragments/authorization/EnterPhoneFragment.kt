package com.example.myapplication.presentation.fragments.authorization

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.presentation.LoginActivity
import com.example.myapplication.presentation.fragments.MainFragment
import com.example.myapplication.presentation.fragments.registration.RegistrationNameFragment
import com.example.myapplication.presentation.utils.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class EnterPhoneFragment(val isRegistration: Boolean = false) : Fragment() {
    private lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private lateinit var mContext: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.enter_phone_fragment, container, false)
        return view
    }

    fun createCallbeck(phoneNumber: String): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
        return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(сredential: PhoneAuthCredential) {
                Auth.signInWithCredential(сredential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        if (!isRegistration) {
                            parentFragmentManager.popBackStack()
                            replaceFragment(parentFragmentManager, MainFragment())
                        } else {
                            RegistrationNameFragment()
                        }
                    } else makeToast(it.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                makeToast("Не ввел телефон")
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                val bundle = Bundle()
                bundle.putString("id", id)
                bundle.putString("phoneNumber", phoneNumber)
                bundle.putBoolean("isRegistration", isRegistration)
                val enterCodeFragment = EnterCodeFragment.getNewInstance(bundle)
                parentFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.login_activity_container, enterCodeFragment)
                    .commit()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onStart() {
        super.onStart()
        val inputPhone = view?.findViewById<EditText>(R.id.input_department)
        val btn = view?.findViewById<FloatingActionButton>(R.id.add_person_to_firebase_btn)
        btn?.setOnClickListener {
            val phoneNumber = inputPhone?.text.toString()
            if (phoneNumber.isNotEmpty()) {
                callback = createCallbeck(phoneNumber)
                authUser(phoneNumber)
            } else makeToast("Не ввели телефон телефон")
        }
    }

    private fun authUser(phoneNumber: String) {
        PhoneAuthProvider.verifyPhoneNumber(
            PhoneAuthOptions
                .newBuilder(FirebaseAuth.getInstance())
                .setPhoneNumber(phoneNumber)
                .setTimeout(120L, TimeUnit.SECONDS)
                .setActivity(activity as LoginActivity)
                .setCallbacks(callback)
                .build()
        )
    }
}