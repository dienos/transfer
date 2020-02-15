package com.jth.transfer.usecase

import android.content.Context
import com.jth.transfer.R
import com.jth.transfer.model.SendType
import com.jth.transfer.model.TransferSendData

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

        return context.getString(R.string.send_msg, name, number, money)
    }
}