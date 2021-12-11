package com.nextint.alodokterbykelompok2.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
import com.jakewharton.rxbinding2.widget.RxTextView
import com.nextint.alodokterbykelompok2.R

object ReactiveField {
    @SuppressLint("CheckResult")
    fun emailStream(view: TextView) =
        RxTextView.textChanges(view)
            .skipInitialValue()
            .map { !Patterns.EMAIL_ADDRESS.matcher(it).matches() }

    fun minLengthStream(view: TextView, length : Int) =
        RxTextView.textChanges(view)
            .skipInitialValue()
            .map { it.length < length}

    fun helperText(isValid : Boolean, view : TextInputLayout, message : Int, context: Context){
        view.helperText = if (isValid) context.getString(message) else null
    }

    fun blankFieldStream(view: TextView) =
        RxTextView.textChanges(view)
            .skipInitialValue()
            .map { it.isBlank() }

    fun buttonState(state : Boolean, view : Button, context: Context){
        if (!state) {
            view.isEnabled = true
            view.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.primary
                )
            )
        } else {
            view.isEnabled = false
            view.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.gsText
                )
            )
        }
    }

}