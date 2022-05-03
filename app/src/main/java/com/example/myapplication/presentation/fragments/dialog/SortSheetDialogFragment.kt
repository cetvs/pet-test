package com.example.myapplication.presentation.fragments.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog
import com.example.app.adapters.MyRecyclerAdapter
import com.example.myapplication.R
import com.example.myapplication.presentation.fragments.tabs.PeopleFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class SortSheetDialogFragment(val listener : RadioGroup.OnCheckedChangeListener) : BottomSheetDialogFragment() {
    companion object {
        const val TAG = "ModalBottomSheet"

        fun getNewInstance(listener : RadioGroup.OnCheckedChangeListener): SortSheetDialogFragment {
            return SortSheetDialogFragment(listener)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext()).create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sort_bottom_sheet, container, false)
        val sheet = view.findViewById<View>(R.id.sort_sheet)
        BottomSheetBehavior.from(sheet).apply{
            peekHeight = 200
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val radioGroup = view.findViewById<RadioGroup>(R.id.radio_group)
        radioGroup.setOnCheckedChangeListener(listener)
        return view
    }

}