package com.study.carton.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseViewHolder
import com.gyf.immersionbar.ImmersionBar
import com.study.carton.R
import com.study.carton.ui.widget.dialog.LoadingDialog
import me.yokeyword.fragmentation.SupportActivity


abstract class BaseActivity : SupportActivity(), IBaseView {

    private var mLoadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        initStatusBar()
        init(savedInstanceState)

        /*Looper.myQueue().addIdleHandler {
            false
        }*/
    }


    inline fun <reified T : RecyclerView.Adapter<BaseViewHolder>> getAdapter(recyclerView: RecyclerView): T? {
        recyclerView.adapter?.apply {
            return this as T
        }
        return null
    }

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun init(savedInstanceState: Bundle?)

    private fun initStatusBar() {
        ImmersionBar.with(this)
            .flymeOSStatusBarFontColor(R.color.black)  //修改flyme OS状态栏字体颜色
            .statusBarDarkFont(true)
            .transparentStatusBar()
            .keyboardEnable(true).init()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    fun setToolBar(
        toolBar: androidx.appcompat.widget.Toolbar,
        title: String?,
        needBackButton: Boolean = true
    ) {
        setSupportActionBar(toolBar)
        val supportActionBar = supportActionBar
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(needBackButton)
            supportActionBar.setDisplayShowHomeEnabled(needBackButton)
            supportActionBar.title = ""
        }
//        val tvTitle = toolBar.findViewById<TextView>(R.id.ac_title)
//        tvTitle?.text = title

        if (needBackButton) {
            toolBar.setNavigationOnClickListener {
                finish()
            }
        }
    }


    fun setToolBarTitle(toolBar: androidx.appcompat.widget.Toolbar, title: String?) {
//        val tvTitle = toolBar.findViewById<TextView>(R.id.ac_title)
//        tvTitle?.text = title
    }


    override fun showLoading() {
        if (this.isFinishing || this.isDestroyed) {
            return
        }
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(this)
        }
        if (!mLoadingDialog!!.isShowing) {
            mLoadingDialog!!.show()
        }
    }

    override fun hideLoading() {
        if (this.isFinishing || this.isDestroyed) {
            return
        }

        if (isLoadingDialogShowing()) {
            mLoadingDialog?.dismiss()
            mLoadingDialog = null
        }
    }

    private fun isLoadingDialogShowing(): Boolean {
        return mLoadingDialog != null && mLoadingDialog!!.isShowing
    }

    fun toast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }

}