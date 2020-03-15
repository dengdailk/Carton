package com.study.carton.ui.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.carton.R
import com.study.carton.bean.search.SeachHotResponse

class HotTagAdapter(list: List<SeachHotResponse.HotItemsBean>) :
    BaseQuickAdapter<SeachHotResponse.HotItemsBean, BaseViewHolder>(
        R.layout.item_tag_hot_list,
        list
    ) {
    override fun convert(
        helper: BaseViewHolder,
        item: SeachHotResponse.HotItemsBean?
    ) {
        helper.apply {
            item?.also {
                setText(R.id.tv_title, it.name)
                addOnClickListener(R.id.tv_title)
            }
        }

    }
}