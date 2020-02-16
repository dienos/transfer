package com.jth.transfer.usecase

import android.app.Activity
import android.content.Context
import com.jth.transfer.model.SendType
import com.jth.transfer.model.TransferSendData
import android.content.Intent
import android.net.Uri
import com.jth.transfer.util.Const


class SendMoneyActivityUseCase(private val context : Context) : BaseUseCase(context) {

    fun getSendMsg(data : TransferSendData) : String {
        var name  = ""
        var number = ""

        val money = data.sendMoneyAmount

        when(data.deposit.type) {
            SendType.ACCOUNT-> {
                name = data.deposit.accountHolder
                number = data.deposit.accountNumber
            }

            SendType.CONTACT-> {
                name = data.deposit.name
                number = data.deposit.phoneNumber
            }
        }

        return context.getString(com.jth.transfer.R.string.send_msg, name, number, money)
    }

    fun startTransferUrlScheme(url : String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        (context as Activity).startActivityForResult(intent, Const.ACTIVITY_RESULT_CODE)
    }
}