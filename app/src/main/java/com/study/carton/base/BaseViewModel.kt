package com.study.carton.base

import android.text.TextUtils
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.study.carton.bean.comm.ComicResponse
import com.study.carton.http.ExceptionHandle
import kotlinx.coroutines.*

open class BaseViewModel : ViewModel(), LifecycleObserver {

    //异常数据
    val mException: MutableLiveData<Throwable> = MutableLiveData()


    /**
     * 用来包裹协程的错误信息
     */
    private suspend fun <T> tryCatch(tryBlock: suspend CoroutineScope.() -> ComicResponse<T>): ComicResponse<T> {
        return coroutineScope {
            try {
                return@coroutineScope tryBlock()
            } catch (e: Throwable) {
                e.printStackTrace()
                val message = ExceptionHandle.handleException(e)?.message
                val response = ComicResponse<T>()
                val dataBean = ComicResponse.DataBean<T>()
                if (!TextUtils.isEmpty(message)) {
                    dataBean.message = message
                } else {
                    dataBean.message = "未知异常"
                }
                dataBean.stateCode = -1
                response.data = dataBean
                response.code = -1
                return@coroutineScope response
            }
        }
    }


     private fun <T> runOnIo(
        request: suspend CoroutineScope.() -> ComicResponse<T>,
        success: ((info: ComicResponse.DataBean<T>) -> Unit),
        error: ((info: ComicResponse<T>) -> Unit)
    ) {
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO) {
                tryCatch(request)
            }
            if (response.code == 1) {
                response.data?.let { success.invoke(it) }
            } else {
                if (response.data != null && !TextUtils.isEmpty(response.data!!.message)) {
                    mException.value = Throwable(response.data!!.message)
                } else {
                    mException.value = Throwable("未知异常")
                }
                error.invoke(response)
            }
        }
    }


    fun <T> request(
        request: suspend CoroutineScope.() -> ComicResponse<T>,
        success: ((info: ComicResponse.DataBean<T>) -> Unit)
    ) {
        runOnIo(request, success, {})
    }
}


