package com.example.myapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.presentation.fragments.authorization.EnterPhoneFragment
import com.example.myapplication.presentation.fragments.registration.DepartmentInfoFragment
import com.example.myapplication.presentation.fragments.registration.LoginOrRegistrationFragment
import com.example.myapplication.presentation.fragments.registration.RegistrationNameFragment
import com.example.myapplication.presentation.utils.Auth
import com.example.myapplication.presentation.utils.replaceLoginFragment
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }

    override fun onStart() {
        super.onStart()
        Auth = FirebaseAuth.getInstance()
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.login_activity_container, LoginOrRegistrationFragment())
            .commit()
//        replaceLoginFragment(supportFragmentManager, LoginOrRegistrationFragment())
    }
}