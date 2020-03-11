package com.study.carton.ui.bookshelf

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gyf.immersionbar.ImmersionBar
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.study.carton.R
import com.study.carton.base.BaseVMFragment
import kotlinx.android.synthetic.main.home_fragment.*

class BookShelfFragment : BaseVMFragment() , OnRefreshListener,
    BaseQuickAdapter.OnItemChildClickListener {


    private val bookShelfViewModel: BookShelfViewModel by lazy { createViewModel<BookShelfViewModel>() }

    override fun getLayoutId(): Int = R.layout.book_shelf_fragment

    override fun init(savedInstanceState: Bundle?) {

    }

    override fun onRefresh(refreshLayout: RefreshLayout) {

    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {

    }


}
