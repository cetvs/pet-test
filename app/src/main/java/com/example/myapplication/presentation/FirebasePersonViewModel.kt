package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import com.example.myapplication.domain.models.FirebasePerson
import com.example.myapplication.domain.models.Person
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebasePersonViewModel : ViewModel() {
    var reference: DatabaseReference

    init {
        val database = Firebase.database(URL)
        reference = database.getReference("people")
    }

    fun addPersonToFirebase(person: Person) {
        reference.push().setValue(person.toMap())
    }

    fun Person.toMap(): Map<String, Any?> {
        return mutableMapOf<String, Any?>(
        "id" to id,
        "firstName" to firstName,
        "lastName" to lastName,
        "userTage" to userTage,
        "department" to department,
        "position" to position,
        "birthday" to birthday,
        "phone" to phone,
        "birthday" to birthday,
        )
    }

}

private const val URL = "https://contacts-812cb-default-rtdb.europe-west1.firebasedatabase.app"