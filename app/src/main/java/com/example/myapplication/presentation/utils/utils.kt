package com.example.myapplication.presentation.utils

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.myapplication.R

fun Fragment.makeToast(text: String) {
//    val toast = Toast.makeText(this.context, text, Toast.LENGTH_SHORT)
//    toast.show()
}

fun replaceFragmentAll(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    containerId: Int,
    addToBackStack: Boolean = false
) {
    if (addToBackStack)
        fragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(containerId, fragment)
            .commit()
    else
        fragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .commit()
}

fun replaceLoginFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    replaceFragmentAll(
        fragmentManager, fragment, R.id.login_activity_container, addToBackStack
    )
}

fun replaceFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    addToBackStack: Boolean = false
) {
    replaceFragmentAll(
        fragmentManager, fragment, R.id.activity_container, addToBackStack
    )
}

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun Fragment.replaceActivity(replaceActivity: AppCompatActivity) {
    val intent = Intent(activity, replaceActivity::class.java)
    startActivity(intent)
    activity?.finish()
}


//fun clearBackStack(fragmentManager: FragmentManager) {
//    if (fragmentManager.getBackStackEntryCount() > 0) {
//        val entry: FragmentManager.BackStackEntry = fragmentManager.getBackStackEntryAt(0)
//        mFragmentManager.popBackStack(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE)
//    }
//}