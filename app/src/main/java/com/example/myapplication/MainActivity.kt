package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myapplication.R
import com.example.myapplication.presentation.fragments.MainFragment

class MainActivity : AppCompatActivity() {
//    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
//        return super.getDefaultViewModelProviderFactory()
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainFragment = MainFragment()
    }
}
