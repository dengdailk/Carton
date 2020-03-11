package com.study.carton.ui.home

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class HomeSortItemDecoration(space: Int, private val headSpace: Int, private val footSpace: Int) :
    ItemDecoration() {
    private val space: Int = space / 2
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.left = headSpace
                outRect.right = space
            }
            state.itemCount - 1 -> {
                outRect.left = space
                outRect.right = footSpace
            }
            else -> {
                outRect.left = space
                outRect.right = space
            }
        }
    }

}