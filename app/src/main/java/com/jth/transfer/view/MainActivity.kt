package com.jth.transfer.view

import android.os.Bundle
import com.jth.transfer.R
import com.jth.transfer.binding.BaseBindingActivity
import com.jth.transfer.databinding.ActivityMainBinding
import com.jth.transfer.repo.TransferRepository
import com.jth.transfer.usecase.MainActivityUseCase
import com.jth.transfer.viewModel.MainViewModel

class MainActivity : BaseBindingActivity<ActivityMainBinding>() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = TransferRepository(this)
        binding.lifecycleOwner = this
        binding.viewModel = MainViewModel(MainActivityUseCase(this), repo)
        (binding.viewModel as MainViewModel).getWithdrawAccount()
    }
}
