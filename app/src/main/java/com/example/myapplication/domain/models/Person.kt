package com.example.myapplication.domain.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
class Person(@PrimaryKey var id: String,
             var avatarUrl: String?,
             var firstName: String?,
             var lastName: String?,
             var userTage: String?,
             var department: String?,
             var position: String?,
             var birthday: String?,
             var phone: String?   ) : Parcelable {
    //@PrimaryKey
//    var id: String? = null
//    var avatarUrl: String? = null
//    var firstName: String? = null
//    var lastName: String? = null
//    var userTag: String? = null
//    var department: String? = null
//    var position: String? = null
//    var birthday: String? = null
//    var phone: String? = null

//    constructor(parcel: Parcel) : this() {
//        id = parcel.readString()
//        avatarUrl = parcel.readString()
//        firstName = parcel.readString()
//        lastName = parcel.readString()
//        userTag = parcel.readString()
//        department = parcel.readString()
//        position = parcel.readString()
//        birthday = parcel.readString()
//        phone = parcel.readString()
//    }

//    override fun toString(): String {
//        return name!!
//    }

//    constructor(id: String?,
//                avatarUrl_: String?,
//                firstName_: String?,
//                lastName_: String?,
//                userTage_: String?,
//                department_: String?,
//                position_: String?,
//                birthday_: String?,
//                phone_: String?)
//    {
//        this.id  = id
//        this.avatarUrl = avatarUrl_
//        this.firstName = firstName_
//        this.lastName = lastName_
//        this.userTag = userTage_
//        this.department = department_
//        this.position = position_
//        this.birthday = birthday_
//        this.phone = phone_
//
//    }


    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(avatarUrl)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(userTage)
        parcel.writeString(department)
        parcel.writeString(position)
        parcel.writeString(birthday)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }


}