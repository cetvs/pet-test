package com.example.myapplication.domain.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FirebasePerson(
    var avatarUrl: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var userTage: String? = null,
    var department: String? = null,
    var position: String? = null,
    var birthday: String? = null,
    var phone: String? = null
) {

}