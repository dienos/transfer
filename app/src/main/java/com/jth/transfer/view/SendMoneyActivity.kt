package com.jth.transfer.view

import android.os.Bundle
import com.jth.transfer.R
import com.jth.transfer.binding.BaseBindingActivity
import com.jth.transfer.databinding.ActivitySendMoneyBinding
import com.jth.transfer.model.TransferSendData
import com.jth.transfer.repo.TransferRepository
import com.jth.transfer.usecase.SendMoneyActivityUseCase
import com.jth.transfer.util.RxBus
import com.jth.transfer.viewModel.SendMoneyViewModel
import io.reactivex.disposables.Disposable

class SendMoneyActivity : BaseBindingActivity<ActivitySendMoneyBinding>() {
    private lateinit var disposable: Disposable

    override fun getLayoutResId(): Int {
        return R.layout.activity_send_money
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = TransferRepository.getInstance(applicationContext)
        binding.lifecycleOwner = this
        binding.viewModel = SendMoneyViewModel(SendMoneyActivityUseCase(this), repo)

        disposable = RxBus.listen(TransferSendData::class.java).subscribe {
            repo.transferSendData = it
        }
    }
}