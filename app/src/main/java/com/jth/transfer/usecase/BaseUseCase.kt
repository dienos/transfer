package com.jth.transfer.usecase

import android.app.Activity
import android.content.Context
import android.widget.Toast

open class BaseUseCase (private val context: Context) {
    fun showToast(msg : String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun setResultOk() {
        (context as Activity).setResult(Activity.RESULT_OK)
    }

    fun finish() {
        (context as Activity).finish()
    }

    fun showDialog() {

    }

    fun showToast(id : Int) {
        Toast.makeText(context, context.getString(id), Toast.LENGTH_SHORT).show()
    }
}