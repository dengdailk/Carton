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
import com.study.carton.ui.detail.ComicDetailActivity
import kotlinx.android.synthetic.main.book_shelf_fragment.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.home_fragment.refreshLayout
import kotlinx.android.synthetic.main.home_fragment.rv_list

class BookShelfFragment : BaseVMFragment() , OnRefreshListener,
    BaseQuickAdapter.OnItemChildClickListener {


    private val bookShelfViewModel: BookShelfViewModel by lazy { createViewModel<BookShelfViewModel>() }

    override fun getLayoutId(): Int = R.layout.book_shelf_fragment

    override fun init(savedInstanceState: Bundle?) {
        refreshLayout.setEnableLoadMore(false)
        refreshLayout.setOnRefreshListener(this)
        ImmersionBar.setTitleBar(_mActivity, tv_title)
        bookShelfViewModel.mCollection.observe(this, Observer {
            val text = String.format(getString(R.string.bookshelf), it.size)
            tv_title.text = text
            if (rv_list.adapter == null) {
                val adapter = BookShelfAdapter(it)
                rv_list.addItemDecoration(BookShelfDecoration())
                adapter.bindToRecyclerView(rv_list)
                adapter.onItemChildClickListener = this
                rv_list.layoutManager = GridLayoutManager(_mActivity, 3)
            } else {
                val adapter = rv_list.adapter as BookShelfAdapter
                adapter.setNewData(it)
            }
            refreshLayout.finishRefresh()
        })
        bookShelfViewModel.getAllCollection()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        bookShelfViewModel.getAllCollection()
    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        adapter?.apply {
            this as BookShelfAdapter
            getItem(position)?.apply {
                ComicDetailActivity.openActivity(_mActivity,comicId)
            }
        }
    }

   }
