package com.study.carton.base

import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider


abstract class BaseVMFragment : BaseFragment() {

    /**
     * 初始化默认的viewModel
     */
    inline fun <reified VM : BaseViewModel> createViewModel(): VM {
        val mViewModel = ViewModelProvider(this)[VM::class.java]
        mViewModel.mException.observe(this, Observer {
            hideLoading()
            toastMessage(it)
        })
        return mViewModel
    }

    open fun toastMessage(throwable: Throwable) {
        if (!TextUtils.isEmpty(throwable.message)) {
            Toast.makeText(_mActivity, throwable.message, Toast.LENGTH_LONG).show()
        }
    }

    open fun onError(throwable: Throwable) {

    }
}