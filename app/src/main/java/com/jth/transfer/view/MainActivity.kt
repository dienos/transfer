package com.jth.transfer.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.jth.transfer.R
import com.jth.transfer.binding.BaseBindingActivity
import com.jth.transfer.databinding.ActivityMainBinding
import com.jth.transfer.repo.TransferRepository
import com.jth.transfer.usecase.MainActivityUseCase
import com.jth.transfer.util.Const
import com.jth.transfer.viewModel.MainViewModel

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = TransferRepository.getInstance()
        binding.lifecycleOwner = this
        binding.viewModel = MainViewModel(MainActivityUseCase(this), repo)
        (binding.viewModel as MainViewModel).getWithdrawAccount()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK
            && requestCode == Const.ACTIVITY_RESULT_CODE) {
            (binding.viewModel as MainViewModel).refreshData()
        }
    }
}
