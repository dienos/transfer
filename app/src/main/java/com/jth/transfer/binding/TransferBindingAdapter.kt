package com.jth.transfer.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jth.transfer.model.WithdrawAccountResult
import com.jth.transfer.view.WithdrawAccountListAdapter
import com.jth.transfer.viewModel.MainViewModel

@BindingAdapter(value = ["withdrawAccount", "viewModel"])
fun setwithdrawAccounts(view: RecyclerView, items: WithdrawAccountResult, vm: MainViewModel) {
    view.adapter?.run {
        if (this is WithdrawAccountListAdapter) {
            data = items.list
            notifyDataSetChanged()
        }
    } ?: run {
        WithdrawAccountListAdapter(vm).apply {
            data = items.list
            view.adapter = this
        }
    }
}

@BindingAdapter(value = ["img_url"])
fun setFinancialImg(view: ImageView, url : String) {
    Glide.with(view.context).load(url).into(view)
}