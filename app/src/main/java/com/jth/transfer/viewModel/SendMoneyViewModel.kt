package com.jth.transfer.viewModel

import com.jth.transfer.interf.DataCallBackListener
import com.jth.transfer.model.ReqTransferAccount
import com.jth.transfer.model.ReqTransferContact
import com.jth.transfer.model.SendType
import com.jth.transfer.repo.TransferRepository
import com.jth.transfer.usecase.SendMoneyActivityUseCase

class SendMoneyViewModel (private val useCae : SendMoneyActivityUseCase, private val repo : TransferRepository) {
    var sendMsg = ""

    init {
        sendMsg =  useCae.getSendMsg(repo.transferSendData)
    }

    fun startTransfer() {
        val deposit = repo.transferSendData.deposit
        val withdrawAccount = repo.transferSendData.withdrawAccount
        val type = deposit.type
        val amount = repo.transferSendData.sendMoneyAmount

        when(type) {
            SendType.ACCOUNT -> {
                val data = ReqTransferAccount()
                data.accountNumber = withdrawAccount.accountNumber
                data.amount = amount.toInt()
                data.receiverAccountNumber = deposit.accountNumber

                repo.postAccountTransfer(data, object : DataCallBackListener {
                    override fun onSuccess(items: Any) {
                        useCae.setResultOk()
                        useCae.finish()
                    }

                    override fun onFailure(message: String?) {
                        message?.apply {
                            useCae.showToast(message)
                        }
                    }
                })
            }

            SendType.CONTACT -> {
                val data = ReqTransferContact()
                data.accountNumber = withdrawAccount.accountNumber
                data.amount = amount.toInt()
                data.receiverPhoneNumber = deposit.phoneNumber

                repo.postContactTransfer(data, object : DataCallBackListener {
                    override fun onSuccess(items: Any) {
                        useCae.setResultOk()
                        useCae.finish()
                    }

                    override fun onFailure(message: String?) {
                        message?.apply {
                            useCae.showToast(message)
                        }
                    }
                })
            }
        }
    }
}