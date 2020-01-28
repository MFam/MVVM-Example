package com.mfamstory.rxmvvmtest.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mfamstory.rxmvvmtest.TAG
import com.mfamstory.rxmvvmtest.data.network.GithubAPI
import com.mfamstory.rxmvvmtest.data.network.model.Repository
import com.mfamstory.rxmvvmtest.util.SingleLiveEvent
import com.mfamstory.rxmvvmtest.util.hideKeyboard
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(val gitAPI : GithubAPI) : DisposableViewModel() {

    private var et_query : String = ""

    private var _items : MutableLiveData<List<Repository>> = MutableLiveData()
    val items : MutableLiveData<List<Repository>> get() = _items

    private var _isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> get() = _isLoading

    private val _clickOpenBrowser = SingleLiveEvent<String>()
    val clickOpenBrower : LiveData<String> get() = _clickOpenBrowser

    private val _hideKeyboard = SingleLiveEvent<Boolean>()
    val hideKeyboard : LiveData<Boolean> get() = _hideKeyboard

    init {
        _items.value = arrayListOf()
    }

    @SuppressLint("CheckResult")
    fun doSearch() {
        if (et_query.isEmpty()) {
            return
        }

        _hideKeyboard.call()

        val params = mapOf(
            Pair("q", et_query),
            Pair("sort", "stars")
        )

        addDisposable(gitAPI.search(params)
            .doOnSubscribe { _isLoading.postValue(true) }
            .doOnSuccess { _isLoading.postValue(false) }
            .doOnError { _isLoading.postValue(false) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { res ->
                _items.value = res.repositories
            }, {
                    t -> t.printStackTrace()
            }))
    }

    fun onQueryChange(query : CharSequence) {
        this.et_query = query.toString()
    }

    fun onRefresh() {
        doSearch()
    }

    fun openRepository(item : Repository) {
        _clickOpenBrowser.postValue(item.html_url)
    }
}
