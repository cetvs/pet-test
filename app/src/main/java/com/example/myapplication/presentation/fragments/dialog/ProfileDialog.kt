package com.example.myapplication.presentation.fragments.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.DialogFragment
import com.example.myapplication.presentation.PersonViewModel
import com.example.app.adapters.MyRecyclerAdapter
import com.example.myapplication.data.source.remote.SimpleApi
import com.example.myapplication.domain.models.Person
import com.example.myapplication.R


class ProfileDialog : DialogFragment() {
    private lateinit var mContext: Context

    companion object {
        fun getNewInstance(args: Bundle): DialogFragment {
            val profileFragment = ProfileDialog()
//            profileFragment.arguments?.putParcelable("person", args)
            profileFragment.arguments = args
            return profileFragment
        }
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
//        AlertDialog.Builder(requireContext())
//            .setCancelable(true)
//            .create()
//
//    protected open fun onBackPressed() {
//        requireActivity().onBackPressed()
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun personToView(person: Person, view: View) {
        val name = view.findViewById<TextView>(R.id.tv_name)
        name.setText("${person.firstName}  ${person.lastName}")
        val description = view.findViewById<TextView>(R.id.tv_position)
        description.setText(person.position)
        val phone = view.findViewById<TextView>(R.id.tv_phone)
        phone.setText("+7 ${person.phone}")
        val birthday = view.findViewById<TextView>(R.id.tv_birthday)
        birthday.setText("${person.birthday}")


        phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8" + person.phone))
            startActivity(intent)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.person_profile, container, false)

        val person = arguments?.getParcelable("person_key") as Person?
        personToView(person!!, view)

        val button = view.findViewById<AppCompatImageButton>(R.id.btn_back)
        button.setOnClickListener{
            dismiss()
        }

        return view
    }

//    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
//        val inflater = super.onGetLayoutInflater(savedInstanceState)
//        val contextThemeWrapper: Context = ContextThemeWrapper(requireContext(), R.style.DialogTheme)
//        return inflater.cloneInContext(contextThemeWrapper)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
    }

//    override fun onStart() {
//        super.onStart()
//        dialog?.let {
//            val width = ViewGroup.LayoutParams.MATCH_PARENT
//            val height = ViewGroup.LayoutParams.MATCH_PARENT
//            it.window?.setLayout(width, height)
//        }
//    }


}