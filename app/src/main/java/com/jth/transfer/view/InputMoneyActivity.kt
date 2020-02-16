package com.jth.transfer.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.jth.transfer.R
import com.jth.transfer.binding.BaseBindingActivity
import com.jth.transfer.databinding.ActivityInputMoneyBinding
import com.jth.transfer.model.TransferSendData
import com.jth.transfer.repo.TransferRepository
import com.jth.transfer.usecase.InputMoneyActivityUseCase
import com.jth.transfer.util.Const
import com.jth.transfer.util.RxBus
import com.jth.transfer.viewModel.InputMoneyViewModel
import io.reactivex.disposables.Disposable

class InputMoneyActivity : BaseBindingActivity<ActivityInputMoneyBinding>() {
    private lateinit var disposable: Disposable

    override fun getLayoutResId(): Int {
        return R.layout.activity_input_money
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = TransferRepository.getInstance()
        binding.lifecycleOwner = this
        binding.viewModel = InputMoneyViewModel(InputMoneyActivityUseCase(this), repo)

        disposable = RxBus.listen(TransferSendData::class.java).subscribe {
            repo.transferSendData = it
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK
            && requestCode == Const.ACTIVITY_RESULT_CODE) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}