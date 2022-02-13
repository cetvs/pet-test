package com.example.myapplication.presentation.adapters

import com.example.myapplication.domain.models.Person

interface OnItemClickListener{
    fun onItemClick(person: Person)
//    fun onImageClick(movie: Person, favImageView: ImageView)
}