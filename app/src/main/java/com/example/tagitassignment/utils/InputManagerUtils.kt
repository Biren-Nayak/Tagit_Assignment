package com.example.tagitassignment.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

object InputManagerUtils {

    private lateinit var imm: InputMethodManager

    private fun Activity.initiallizeImm() {
        imm = getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    fun Activity.hideSoftKeyboard(targetEditText: EditText) {
        initiallizeImm()
        imm.hideSoftInputFromWindow(targetEditText.windowToken,
            InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }


    fun Activity.showSoftKeyboard(editText: EditText) {
        initiallizeImm()
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

}