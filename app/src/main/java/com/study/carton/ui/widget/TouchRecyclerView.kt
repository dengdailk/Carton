package com.study.carton.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * 具体的作用：只要点击事件。滑动的话不算
 */
class TouchRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {
    private val touchSlop: Int = ViewConfiguration.get(context).scaledTouchSlop
    private var startX = -1f
    private var startY = -1f
    private lateinit var mIMiddleCallBack: ITouchCallBack
    fun setITouchCallBack(IMiddleCallBack: ITouchCallBack?) {
        if (IMiddleCallBack != null) {
            this.mIMiddleCallBack = IMiddleCallBack
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
            }
            MotionEvent.ACTION_UP -> {
                if (abs(event.x - startX) > touchSlop) startX = -1f
                if (abs(event.y - startY) > touchSlop) startX = -1f
                if (startX != -1f) {
                    mIMiddleCallBack.click()
                }
            }
            else -> {
            }
        }
        return super.onTouchEvent(event)
    }

    interface ITouchCallBack {
        fun click()
    }
}