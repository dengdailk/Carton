package com.study.carton.ui.widget.dialog

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialog
import com.study.carton.R
import kotlinx.android.synthetic.main.layout_loading.*


class LoadingDialog(context: Context,theme: Int = R.style.Theme_AppCompat_Dialog) : AppCompatDialog(context,theme) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.layout_loading)
            setWindowSize()
    }




    override fun dismiss() {
//        animation_view.cancelAnimation()
        super.dismiss()
    }

    /**
     * 设置窗口尺寸和位置
     */
    private fun setWindowSize() {
        val dialogWindow = this.window
        dialogWindow!!.setBackgroundDrawableResource(android.R.color.transparent)//设置透明
        val lp = dialogWindow.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        lp.y = getDistanceY()
        dialogWindow.attributes = lp
    }

    /**
     * 弹窗Y轴偏移量
     */
    private fun getDistanceY(): Int {
        return 0
    }
}