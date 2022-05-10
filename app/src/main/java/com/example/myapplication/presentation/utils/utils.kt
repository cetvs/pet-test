package com.example.myapplication.presentation

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R

fun makeToast(context: Context, text: String) {
    val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    toast.show()
}

fun replaceFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    if (addToBackStack)
        fragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.activity_container, fragment)
            .commit()
    else
        fragmentManager
            .beginTransaction()
            .replace(R.id.activity_container, fragment)
            .commit()
}



//fun clearBackStack(fragmentManager: FragmentManager) {
//    if (fragmentManager.getBackStackEntryCount() > 0) {
//        val entry: FragmentManager.BackStackEntry = fragmentManager.getBackStackEntryAt(0)
//        mFragmentManager.popBackStack(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE)
//    }
//}