package com.jth.transfer.viewModel

import com.jth.transfer.interf.DataCallBackListener
import com.jth.transfer.model.WithdrawAccountResult
import com.jth.transfer.repo.NotNullMutableLiveData
import com.jth.transfer.repo.TransferRepository
import com.jth.transfer.usecase.MainActivityUseCase

class MainViewModel(private val useCae : MainActivityUseCase, private val repo : TransferRepository) {
    var withdrawAccountResult: NotNullMutableLiveData<WithdrawAccountResult> = NotNullMutableLiveData(WithdrawAccountResult())

    fun startInputMoney() {

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
}