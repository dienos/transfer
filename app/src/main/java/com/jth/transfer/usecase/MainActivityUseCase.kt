package com.jth.transfer.usecase

import android.content.Context
import android.widget.Toast

class MainActivityUseCase(val context : Context) {
    fun startInputMoney() {

    }

    fun showToast(msg : String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }
}