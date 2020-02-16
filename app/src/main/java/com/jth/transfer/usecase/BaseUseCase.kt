package com.jth.transfer.usecase

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.Toast
import com.jth.transfer.R

open class BaseUseCase (private val context: Context) {
    private val loading : Dialog = Dialog(context)

    init {
        loading.setContentView(R.layout.dialog)
    }

    fun getString(id : Int) : String {
        return context.getString(id)
    }

    fun getString(id : Int, msg : String, msg2 : String) : String {
        return context.getString(id, msg, msg2)
    }

    fun showToast(msg : String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun setResultOk() {
        (context as Activity).setResult(Activity.RESULT_OK)
    }

    fun finish() {
        (context as Activity).finish()
    }

    fun showToast(id : Int) {
        Toast.makeText(context, context.getString(id), Toast.LENGTH_SHORT).show()
    }

    fun showLoading() {
        val activity = context as Activity

        dismissLoading()

        if (!activity.isFinishing) {
            loading.setOwnerActivity(activity)
            loading.setCanceledOnTouchOutside(false)
            loading.setCancelable(false)
            loading.show()
        }
    }

    fun dismissLoading() {
        try {
            if (loading.isShowing) {
                loading.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}