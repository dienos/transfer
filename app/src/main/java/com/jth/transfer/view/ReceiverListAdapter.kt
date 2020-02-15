package com.jth.transfer.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jth.transfer.BR
import com.jth.transfer.R
import com.jth.transfer.databinding.ItemRecieverBinding
import com.jth.transfer.model.Receiver
import com.jth.transfer.viewModel.SelectReceiverViewModel

class ReceiverListAdapter(private val viewModel: SelectReceiverViewModel) : RecyclerView.Adapter<ReceiverListAdapter.ViewHolder>() {

    var data: ArrayList<Receiver> = arrayListOf()

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(viewModel, data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reciever, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(viewModel: SelectReceiverViewModel, withdrawAccount: Receiver?) {
            val binding: ItemRecieverBinding = DataBindingUtil.bind(view)!!

            withdrawAccount?.let {
                binding.setVariable(BR.receiverItem, it)
            }

            binding.viewModel = viewModel
        }
    }
}