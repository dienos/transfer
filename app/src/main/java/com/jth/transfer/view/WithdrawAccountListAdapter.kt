package com.jth.transfer.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jth.transfer.BR
import com.jth.transfer.R
import com.jth.transfer.databinding.ItemWithdrawAccountBinding
import com.jth.transfer.model.WithdrawAccount
import com.jth.transfer.viewModel.MainViewModel

class WithdrawAccountListAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<WithdrawAccountListAdapter.ViewHolder>() {

    var data: ArrayList<WithdrawAccount> = arrayListOf()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_withdraw_account, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(viewModel: MainViewModel, withdrawAccount: WithdrawAccount?) {
            val binding: ItemWithdrawAccountBinding = DataBindingUtil.bind(view)!!

            withdrawAccount?.let {
                binding.setVariable(BR.accountItem, it)
            }

            binding.viewModel = viewModel
        }
    }
}