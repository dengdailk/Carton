package com.study.carton.ui.home

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.carton.R
import com.study.carton.bean.home.RecommendResponse
import com.study.carton.utils.DisplayUtils
import com.study.carton.utils.GlideUtils

class HomeListItemAdapter1(data: List<RecommendResponse.ComicListsBean.ComicsBean>) :
    BaseQuickAdapter<RecommendResponse.ComicListsBean.ComicsBean, BaseViewHolder>(
        R.layout.item_type_content_item_1,
        data
    ) {

    private val imageWidth: Int = DisplayUtils.dp2px(97f)
    private val imageHeight: Int = DisplayUtils.dp2px(160f)

    override fun convert(
        helper: BaseViewHolder,
        item: RecommendResponse.ComicListsBean.ComicsBean?
    ) {
        helper.apply {
            item?.also {
                setGone(R.id.v_item_right_bg, adapterPosition == 0)
                setGone(R.id.v_item_left_bg, adapterPosition == data.size - 1)
                setText(R.id.tv_title, it.name)
                addOnClickListener(R.id.frameLayout)
                GlideUtils.loadImage(
                    mContext, it.cover, getView(R.id.iv_cover),
                    0f, imageWidth, imageHeight
                )
            }
        }
    }
}