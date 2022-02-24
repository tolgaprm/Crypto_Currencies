package com.inflames.curenciesviewmodel.currencylistscreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.inflames.curenciesviewmodel.databinding.CryptoRowItemBinding
import com.inflames.curenciesviewmodel.model.CryptoModel

class CryptoListAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<CryptoModel, CryptoListAdapter.CryptoViewHolder>(CryptoDiffCallBack()) {

    class CryptoViewHolder(val binding: CryptoRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): CryptoViewHolder {
                val binding =
                    CryptoRowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return CryptoViewHolder(binding)
            }
        }

        fun bind(cryptoModel: CryptoModel) {
            binding.crypto = cryptoModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        return CryptoViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        val cryptoModel = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(cryptoModel)
        }
        holder.bind(cryptoModel)


    }


    class OnClickListener(val clickListener: (cryptoModel: CryptoModel) -> Unit) {
        fun onClick(cryptoModel: CryptoModel) = clickListener(cryptoModel)
    }

}


class CryptoDiffCallBack : DiffUtil.ItemCallback<CryptoModel>() {
    override fun areItemsTheSame(oldItem: CryptoModel, newItem: CryptoModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CryptoModel, newItem: CryptoModel): Boolean {
        return oldItem == newItem
    }

}