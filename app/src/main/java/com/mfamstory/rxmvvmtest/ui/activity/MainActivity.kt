package com.mfamstory.rxmvvmtest.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import com.mfamstory.rxmvvmtest.R
import com.mfamstory.rxmvvmtest.databinding.MainActivityBinding
import com.mfamstory.rxmvvmtest.ui.viewmodel.MainViewModel
import com.mfamstory.rxmvvmtest.util.hideKeyboard
import kotlinx.android.synthetic.main.main_activity.*
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : BindingActivity<MainActivityBinding>() {

    override fun getLayoutResId() = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm : MainViewModel = getViewModel()
        binding.vm = vm
        binding.lifecycleOwner = this

        vm.clickOpenBrower.observe(this, Observer {
            url ->
            val uri = Uri.parse(url)
            val bundle = Bundle().apply {

                putBoolean("new_window", true)
            }
            startActivity(Intent(Intent.ACTION_VIEW, uri).putExtras(bundle))
        })

        vm.hideKeyboard.observe(this, Observer {
            hideKeyboard()
        })

        et_query.setOnEditorActionListener({v, actionId, event ->
            when(actionId) {
                EditorInfo.IME_ACTION_SEARCH -> vm.doSearch()
            }
            false
        })
    }
}
