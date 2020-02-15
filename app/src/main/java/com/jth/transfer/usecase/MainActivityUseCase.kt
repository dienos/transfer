package com.jth.transfer.usecase

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.jth.transfer.model.TransferSendData
import com.jth.transfer.model.WithdrawAccount
import com.jth.transfer.util.Const
import com.jth.transfer.util.RxBus
import com.jth.transfer.view.InputMoneyActivity

class MainActivityUseCase(private val context : Context) : BaseUseCase(context)  {
    fun startInputMoney(item : WithdrawAccount) {
        val sendData = TransferSendData()
        sendData.withdrawAccount = item

        RxBus.send(sendData)
        val intent = Intent(context, InputMoneyActivity::class.java)
        (context as Activity).startActivityForResult(intent, Const.ACTIVITY_RESULT_CODE)
    }
}