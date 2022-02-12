package com.example.myapplication.adapters

import android.view.View
import android.widget.ImageView
import com.example.app.classes.Person

interface OnItemClickListener{
    fun onItemClick(person: Person)
//    fun onImageClick(movie: Person, favImageView: ImageView)
}