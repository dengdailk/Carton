package com.study.carton.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * @author dengdai
 * @date 2020/3/6.
 * GitHub：
 * email：291996307@qq.com
 * description：负责对数据请求做批量管理
 */
open class BaseRepository {
    private val mCompositeSubscription by lazy { CompositeDisposable() }
    fun subscribe(disposable: Disposable) = mCompositeSubscription.add(disposable)
    fun unSubscribe() = mCompositeSubscription.dispose()
}