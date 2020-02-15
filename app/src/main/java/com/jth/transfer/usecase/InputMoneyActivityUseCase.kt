package com.jth.transfer.usecase

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.jth.transfer.model.TransferSendData
import com.jth.transfer.util.Const
import com.jth.transfer.util.RxBus
import com.jth.transfer.view.SelectReceiverActivity

class InputMoneyActivityUseCase(private val context: Context) : BaseUseCase(context) {
    fun getString(id : Int) : String {
        return context.getString(id)
    }

    fun startSelectReceiver(data : TransferSendData) {
        RxBus.send(data)
        val intent = Intent(context, SelectReceiverActivity::class.java)
        (context as Activity).startActivityForResult(intent, Const.ACTIVITY_RESULT_CODE)
    }
}