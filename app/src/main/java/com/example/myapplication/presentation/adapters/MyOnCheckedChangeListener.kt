package com.example.myapplication.presentation.adapters

import android.os.Parcel
import android.os.Parcelable
import android.widget.RadioGroup

class MyOnCheckedChangeListener(
//    val listener: RadioGroup.OnCheckedChangeListener
) : RadioGroup.OnCheckedChangeListener, Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MyOnCheckedChangeListener> {
        override fun createFromParcel(parcel: Parcel): MyOnCheckedChangeListener {
            return MyOnCheckedChangeListener(parcel)
        }

        override fun newArray(size: Int): Array<MyOnCheckedChangeListener?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        TODO("Not yet implemented")
    }
}