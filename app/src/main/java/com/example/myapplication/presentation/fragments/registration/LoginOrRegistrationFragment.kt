package com.example.myapplication.presentation.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.presentation.fragments.authorization.EnterPhoneFragment
import com.example.myapplication.presentation.utils.replaceLoginFragment

class LoginOrRegistrationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.choose_login_or_registration, container, false)
        return view
    }

    override fun onStart() {
        super.onStart()
        setListener()
    }

    private fun setListener() {
        val loginButton = view?.findViewById<Button>(R.id.login_btn)
        loginButton?.setOnClickListener {
            replaceLoginFragment(parentFragmentManager, EnterPhoneFragment())
        }

        val registrationButton = view?.findViewById<Button>(R.id.registration_btn)
        registrationButton?.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.login_activity_container, EnterPhoneFragment(true))
                .commit()
//            replaceLoginFragment(parentFragmentManager, EnterPhoneFragment(true))
        }
    }


}