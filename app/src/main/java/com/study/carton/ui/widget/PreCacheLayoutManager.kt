package com.study.carton.ui.widget

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.study.carton.utils.DisplayUtils

/**
 *
 * @author  Lai
 *
 * @time 2019/10/12 19:29
 * @describe describe
 *
 */
class PreCacheLayoutManager : LinearLayoutManager {
    constructor(context: Context?) : super(context)

    //提前加载一个屏幕
    private val mExtraSpace =
        if (canScrollHorizontally()) DisplayUtils.screenWidth else
            DisplayUtils.screenHeight

    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )
    override fun calculateExtraLayoutSpace(state: RecyclerView.State, extraLayoutSpace: IntArray) {
        extraLayoutSpace[0] = mExtraSpace
        extraLayoutSpace[1] = mExtraSpace
    }

}