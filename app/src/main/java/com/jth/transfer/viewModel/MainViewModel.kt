package com.jth.transfer.viewModel

import com.jth.transfer.R
import com.jth.transfer.interf.DataCallBackListener
import com.jth.transfer.model.TransferSendData
import com.jth.transfer.model.WithdrawAccount
import com.jth.transfer.model.WithdrawAccountResult
import com.jth.transfer.repo.NotNullMutableLiveData
import com.jth.transfer.repo.TransferRepository
import com.jth.transfer.usecase.MainActivityUseCase

class MainViewModel(private val useCae : MainActivityUseCase, private val repo : TransferRepository) {
    var withdrawAccountResult: NotNullMutableLiveData<WithdrawAccountResult> = NotNullMutableLiveData(WithdrawAccountResult())

    fun startInputMoney(item : WithdrawAccount) {
        if(item.balance == "0") {
            useCae.showToast(R.string.amount_limited)
        } else {
            useCae.startInputMoney(item)
        }
    }

    fun getWithdrawAccount() {
        repo.getWithdrawAccount(object : DataCallBackListener {
            override fun onSuccess(items: Any) {
                if(items is WithdrawAccountResult) {
                    withdrawAccountResult.value = items
                }
            }

            override fun onFailure(message: String?) {
                message?.apply {
                    useCae.showToast(this)
                }
            }
        })
    }

    fun refreshData() {
        val result = withdrawAccountResult.value

        withdrawAccountResult.value.list.forEachIndexed {
            i, it ->
            if(it.accountNumber == repo.transferSendData.withdrawAccount.accountNumber) {
                it.balance = it.balance.toInt().minus(repo.transferSendData.sendMoneyAmount.toInt()).toString()
                result.list[i].balance = it.balance
            }
        }

        withdrawAccountResult.value = result
        repo.transferSendData = TransferSendData()
    }
}