package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.presentation.replaceFragment
import com.example.myapplication.presentation.fragments.EnterPhoneFragment
import com.example.myapplication.presentation.fragments.MainFragment
import com.example.myapplication.presentation.utils.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
//    private lateinit var Auth: FirebaseAuth
//    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
//        return super.getDefaultViewModelProviderFactory()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if(savedInstanceState == null){
//        if (savedInstanceState == null) {
//            Auth = FirebaseAuth.getInstance()
//            replaceFragment(supportFragmentManager, EnterPhoneFragment())
//        } else {
//            replaceFragment(supportFragmentManager, MainFragment())
//        }
        val auth = Firebase.auth
        var currentUser = auth.getCurrentUser()
        Log.v("421", "$currentUser")
    }
}
