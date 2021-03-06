package com.example.myapplication.domain.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
class Person(
    @PrimaryKey
    val id: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val userTage: String? = null,
    val department: String? = null,
    val position: String? = null,
    val birthday: String? = null,
    val phone: String? = null,
    val avatarUrl: String? = null
) : Parcelable {

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
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(userTage)
        parcel.writeString(department)
        parcel.writeString(position)
        parcel.writeString(birthday)
        parcel.writeString(phone)
        parcel.writeString(avatarUrl)
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
