package com.mfamstory.rxmvvmtest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mfamstory.rxmvvmtest.R
import com.mfamstory.rxmvvmtest.data.network.model.Repository
import com.mfamstory.rxmvvmtest.databinding.ItemSearchBinding
import com.mfamstory.rxmvvmtest.ui.viewmodel.MainViewModel
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.AnimationAdapter


class RepositoryAdapter(var items : List<Repository>, val vm : MainViewModel) :
    RecyclerView.Adapter<RepositoryAdapter.RepoitoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoitoryViewHolder {
        return RepoitoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RepoitoryViewHolder, position: Int) {
        holder.apply {
            binding.vm = vm
            binding.item = items[position]
        }
    }

    class RepoitoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val binding = DataBindingUtil.bind<ItemSearchBinding>(view)!!
    }
}