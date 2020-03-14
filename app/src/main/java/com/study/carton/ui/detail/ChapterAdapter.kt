package com.study.carton.ui.detail

import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.carton.R
import com.study.carton.bean.detail.ComicDetailResponse


class ChapterAdapter(data: List<ComicDetailResponse.ChapterListBean>,
                     layout: Int = R.layout.item_chapter) :
        BaseQuickAdapter<ComicDetailResponse.ChapterListBean, BaseViewHolder>(
                layout, data
        ) {
    override fun convert(helper: BaseViewHolder, item: ComicDetailResponse.ChapterListBean?) {
        //item type ==3 vip？
        item?.also {
            if (it.type == "3") {
                helper.setTextColor(R.id.tv_title, ContextCompat.getColor(mContext, R.color.color_3333_80))
                helper.setText(R.id.tv_title, mContext.getString(R.string.no_support_vip_tip))
            } else {
                helper.setText(R.id.tv_title, it.name)
                helper.addOnClickListener(R.id.tv_title)
                if (it.isRead) {
                    helper.setTextColor(R.id.tv_title, ContextCompat.getColor(mContext, R.color.color_3333_80))
                    helper.setText(R.id.tv_title, it.name)
                } else {
                    helper.setTextColor(R.id.tv_title, ContextCompat.getColor(mContext, R.color.color_333333))
                }
            }
        }
    }
}