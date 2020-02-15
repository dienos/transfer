package com.jth.transfer.viewModel

import com.jth.transfer.R
import com.jth.transfer.repo.NotNullMutableLiveData
import com.jth.transfer.repo.TransferRepository
import com.jth.transfer.usecase.InputMoneyActivityUseCase

class InputMoneyViewModel(private val useCae : InputMoneyActivityUseCase, private val repo : TransferRepository) {
    var confirmBtnIsEnabled: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    var inputMoney : NotNullMutableLiveData<String> = NotNullMutableLiveData("")

    fun onTextChanged(s: CharSequence, start :Int, before : Int, count: Int){
        val text = s.toString()
        inputMoney.value = text

        if(text.isNotEmpty() && text != "0") {
            if (text.toInt() > 500000) {
                confirmBtnIsEnabled.value = false
                useCae.showToast(useCae.getString(R.string.send_money_limited))
                inputMoney.value = "500000"
            } else {
                confirmBtnIsEnabled.value = true
            }
        } else {
            confirmBtnIsEnabled.value = false
        }
    }

    fun startSelectReceiver() {
        if (repo.transferSendData.withdrawAccount.balance.toInt() < inputMoney.value.toInt()) {
            useCae.showToast(R.string.amount_limited)
        } else {
            repo.transferSendData.sendMoneyAmount = inputMoney.value
            useCae.startSelectReceiver(repo.transferSendData)
        }
    }
}