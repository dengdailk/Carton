package com.study.carton.utils

import android.content.res.Resources
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.View

/**
 * Created by AA on 2017/1/9.
 * DP/PX数值转换工具类
 */
object DisplayUtils {
    /**
     * 获取屏幕宽高信息
     *
     * @return
     */
    val screenMetrics: Point
        get() {
            val dm = Resources.getSystem().displayMetrics
            return Point(dm.widthPixels, dm.heightPixels)
        }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    /**
     * 获取状态栏高度
     *
     * @return
     */
    val statusBarHeight: Int
        get() {
            var result = 0
            val resourceId = Resources.getSystem()
                .getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = Resources.getSystem().getDimensionPixelSize(resourceId)
            }
            return result
        }

    /**
     * 获取导航栏高度
     */
    val navigationBarHeight: Int
        get() {
            var result = 0
            val resourceId = Resources.getSystem()
                .getIdentifier("navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                result = Resources.getSystem().getDimensionPixelSize(resourceId)
            }
            return result
        }

    /**
     * 获取bitmap Density
     *
     * @return bitmapDensity
     */
    val bitmapDensity: Int
        get() {
            val densityDpi =
                Resources.getSystem().displayMetrics.densityDpi
            return if (densityDpi <= DisplayMetrics.DENSITY_LOW) {
                DisplayMetrics.DENSITY_LOW
            } else if (densityDpi <= DisplayMetrics.DENSITY_MEDIUM) {
                DisplayMetrics.DENSITY_MEDIUM
            } else if (densityDpi <= DisplayMetrics.DENSITY_HIGH) {
                DisplayMetrics.DENSITY_HIGH
            } else if (densityDpi <= DisplayMetrics.DENSITY_XHIGH) {
                DisplayMetrics.DENSITY_XHIGH
            } else if (densityDpi <= DisplayMetrics.DENSITY_400) {
                DisplayMetrics.DENSITY_XHIGH
            } else {
                DisplayMetrics.DENSITY_XXHIGH
            }
        }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

    fun px2dp(pxValue: Float): Int {
        val scale =
            Resources.getSystem().displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    fun dp2px(dipValue: Float): Int {
        val scale =
            Resources.getSystem().displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    fun px2sp(pxValue: Float): Int {
        val fontScale =
            Resources.getSystem().displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    fun px2spToFloat(pxValue: Float): Float {
        val fontScale =
            Resources.getSystem().displayMetrics.scaledDensity
        return pxValue / fontScale + 0.5f
    }

    fun sp2px(spValue: Float): Int {
        val fontScale =
            Resources.getSystem().displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    /**
     * 获取View的宽高
     *
     * @param view
     * @return
     */
    fun getViewSize(view: View): IntArray {
        val size = IntArray(2)
        val width = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        val height = View.MeasureSpec.makeMeasureSpec(
            0,
            View.MeasureSpec.UNSPECIFIED
        )
        view.measure(width, height)
        size[0] = view.measuredWidth
        size[1] = view.measuredHeight
        return size
    }
}