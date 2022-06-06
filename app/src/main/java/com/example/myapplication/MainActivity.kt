package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.presentation.LoginActivity
import com.example.myapplication.presentation.utils.replaceFragment
import com.example.myapplication.presentation.fragments.MainFragment
import com.example.myapplication.presentation.utils.Auth
import com.example.myapplication.presentation.utils.replaceActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
//    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
//        return super.getDefaultViewModelProviderFactory()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        authWithFragments()
        replaceFragment(supportFragmentManager, MainFragment())
    }

    fun authWithFragments() {
//        if (Auth.currentUser != null) {
//            replaceFragment(supportFragmentManager, MainFragment())
//        } else {
            replaceActivity(LoginActivity())
//        }
    }
}
