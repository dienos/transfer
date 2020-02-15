package com.jth.transfer.viewModel

import com.jth.transfer.R
import com.jth.transfer.interf.DataCallBackListener
import com.jth.transfer.model.*
import com.jth.transfer.repo.NotNullMutableLiveData
import com.jth.transfer.repo.TransferRepository
import com.jth.transfer.usecase.SelectReceiverActivityUseCase

class SelectReceiverViewModel(private val useCae : SelectReceiverActivityUseCase,
                              private val repo : TransferRepository) {
    var depositAccount = DepositAccountResult()
    var depositContact = DepositContactResult()
    var depositResult: NotNullMutableLiveData<DepositDataResult> = NotNullMutableLiveData(DepositDataResult())

    fun getDepositAccount() {
        repo.getDepositAccount(object : DataCallBackListener {
            override fun onSuccess(items: Any) {
                if(items is DepositAccountResult) {
                    depositAccount = items

                    repo.getDepositContact(object : DataCallBackListener {
                        override fun onSuccess(items: Any) {
                            if(items is DepositContactResult) {
                                depositContact = items
                                depositResult.value = makeDepositData()
                            }
                        }

                        override fun onFailure(message: String?) {
                            message?.apply {
                                useCae.showToast(this)
                            }
                        }
                    })
                }
            }

            override fun onFailure(message: String?) {
                message?.apply {
                    useCae.showToast(this)
                }
            }
        })
    }

    private fun makeDepositData() : DepositDataResult {
        val result = DepositDataResult()
        val list : ArrayList<Receiver> = arrayListOf()

        depositAccount.list.forEach {
            val deposit = Receiver()
            deposit.accountHolder = it.accountHolder
            deposit.accountNumber = it.accountNumber
            deposit.isEnabled = it.isEnabled
            deposit.type = SendType.ACCOUNT
            list.add(deposit)
        }

        depositContact.list.forEach {
            val deposit = Receiver()
            deposit.name = it.name
            deposit.phoneNumber = it.phoneNumber
            deposit.isEnabled = true
            deposit.type = SendType.CONTACT
            list.add(deposit)
        }

        result.list = list

        return result
    }

    fun startSendActivity(receiver: Receiver) {
        if(receiver.isEnabled) {
            repo.transferSendData.deposit = receiver
            useCae.startSend(repo.transferSendData)
        } else {
            if(receiver.type == SendType.ACCOUNT) {
                useCae.showToast(useCae.getString(R.string.deposit_dis_able_account))
            } else {
                useCae.showToast(useCae.getString(R.string.deposit_dis_able_con))
            }
        }
    }
}