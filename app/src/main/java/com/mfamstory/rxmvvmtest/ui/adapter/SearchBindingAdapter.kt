package com.mfamstory.rxmvvmtest.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.mfamstory.rxmvvmtest.data.network.model.Repository
import com.mfamstory.rxmvvmtest.ui.viewmodel.MainViewModel
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter

@BindingAdapter(value = ["items", "viewModel"])
fun setAdapter(view: RecyclerView, listItems: List<Repository>, vm: MainViewModel) {
    view.adapter?.run {
        if (this is AlphaInAnimationAdapter) {
            val adapter = this.wrappedAdapter as RepositoryAdapter
            adapter.items = listItems
            adapter.notifyDataSetChanged()
        }
    } ?: run {
        view.adapter = AlphaInAnimationAdapter(RepositoryAdapter(listItems, vm)).apply {
            setDuration(500)
            setFirstOnly(false)
        }
    }
}

@BindingAdapter(value = ["avatarUrl"])
fun setAvatar(view: ImageView, avatarUrl: String) {
    Glide.with(view.context)
        .load(avatarUrl)
        .transition(DrawableTransitionOptions.withCrossFade(500))
        .into(view)
}
