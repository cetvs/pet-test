package com.example.myapplication.presentation.fragments.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SortSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.sort_bottom_sheet, container, false)

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}