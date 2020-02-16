package com.jth.transfer.viewModel

import com.jth.transfer.interf.DataCallBackListener
import com.jth.transfer.model.ReqTransferAccount
import com.jth.transfer.model.ReqTransferContact
import com.jth.transfer.model.SendType
import com.jth.transfer.repo.TransferRepository
import com.jth.transfer.usecase.SendMoneyActivityUseCase

class SendMoneyViewModel(
    private val useCae: SendMoneyActivityUseCase,
    private val repo: TransferRepository
) {
    var sendMsg = ""

    init {
        sendMsg = useCae.getSendMsg(repo.transferSendData)
    }

    fun startTransfer() {
        val deposit = repo.transferSendData.deposit
        val withdrawAccount = repo.transferSendData.withdrawAccount
        val type = deposit.type
        val amount = repo.transferSendData.sendMoneyAmount

        when (type) {
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

    fun startTransferUrlScheme() {
        val deposit = repo.transferSendData.deposit
        val type = deposit.type
        val withdrawAccount = repo.transferSendData.withdrawAccount
        val amount = repo.transferSendData.sendMoneyAmount
        var url = ""

        when (type) {
            SendType.ACCOUNT -> {
                makeUrlSendTypeAccount(
                    withdrawAccount.accountNumber,
                    amount,
                    deposit.accountNumber
                ).let {
                    url = it
                }
            }

            SendType.CONTACT -> {
                makeUrlSendTypeContact(
                    withdrawAccount.accountNumber,
                    amount,
                    deposit.phoneNumber
                ).let {
                    url = it
                }
            }
        }

        useCae.startTransferUrlScheme(url)
    }

    private fun makeUrlSendTypeAccount(
        accountNumber: String,
        amount: String,
        receiverAccountNumber: String
    ): String {
        return "test://transfer/account?accountNumber=".plus(accountNumber).plus("&amount=")
            .plus(amount).plus("&receiverAccountNumber=").plus(receiverAccountNumber)
    }

    private fun makeUrlSendTypeContact(
        accountNumber: String,
        amount: String,
        receiverPhoneNumber: String
    ): String {
        return "test://transfer/contact?accountNumber=".plus(accountNumber).plus("&amount=")
            .plus(amount).plus("&receiverPhoneNumber=").plus(receiverPhoneNumber)
    }
}