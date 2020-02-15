package com.jth.transfer.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jth.transfer.model.DepositDataResult
import com.jth.transfer.model.WithdrawAccountResult
import com.jth.transfer.view.ReceiverListAdapter
import com.jth.transfer.view.WithdrawAccountListAdapter
import com.jth.transfer.viewModel.MainViewModel
import com.jth.transfer.viewModel.SelectReceiverViewModel

@BindingAdapter(value = ["withdrawAccount", "viewModel"])
fun setWithdrawAccounts(view: RecyclerView, items: WithdrawAccountResult, viewModel: MainViewModel) {
    view.adapter?.run {
        if (this is WithdrawAccountListAdapter) {
            data = items.list
            notifyDataSetChanged()
        }
    } ?: run {
        WithdrawAccountListAdapter(viewModel).apply {
            data = items.list
            view.adapter = this
        }
    }
}

@BindingAdapter(value = ["receiver", "viewModel"])
fun setDeposits(view: RecyclerView, items: DepositDataResult, viewModel: SelectReceiverViewModel) {
    view.adapter?.run {
        if (this is ReceiverListAdapter) {
            data = items.list
            notifyDataSetChanged()
        }
    } ?: run {
        ReceiverListAdapter(viewModel).apply {
            data = items.list
            view.adapter = this
        }
    }
}


@BindingAdapter(value = ["img_url"])
fun setFinancialImg(view: ImageView, url : String) {
    Glide.with(view.context).load(url).into(view)
}