package com.example.myapplication.presentation.fragments.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.domain.models.Person
import com.example.myapplication.presentation.FirebasePersonViewModel
import com.example.myapplication.presentation.fragments.authorization.EnterCodeFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DepartmentInfoFragment : Fragment() {
    lateinit var firebasePersonViewModel: FirebasePersonViewModel

    companion object {
        fun getNewInstance(args: Bundle): DepartmentInfoFragment {
            val departmentInfoFragment = DepartmentInfoFragment()
            departmentInfoFragment.arguments = args
            return departmentInfoFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            R.layout.registration_department_info_fragment, container, false
        )
        return view
    }


    override fun onStart() {
        super.onStart()
        val button = view?.findViewById<FloatingActionButton>(R.id.add_person_to_firebase_btn)
        button?.setOnClickListener {
            val person = createPerson()
            firebasePersonViewModel = FirebasePersonViewModel()
            firebasePersonViewModel.addPersonToFirebase(person)
        }
    }

    private fun createPerson(): Person {
        val department = view?.findViewById<EditText>(R.id.input_department)
        val position = view?.findViewById<EditText>(R.id.input_position)
        val birthday = view?.findViewById<EditText>(R.id.input_birthday)
        return Person(
            arguments?.getString("id")!!,
            arguments?.getString("name"),
            arguments?.getString("surname"),
            arguments?.getString("tag"),
            department?.text.toString(),
            position?.text.toString(),
            birthday?.text.toString(),
            arguments?.getString("phone")
        )
    }
}