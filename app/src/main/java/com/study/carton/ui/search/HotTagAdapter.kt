package com.study.carton.ui.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.carton.R
import com.study.carton.bean.search.SearchHotResponse


class HotTagAdapter(list: List<SearchHotResponse.HotItemsBean>) :
    BaseQuickAdapter<SearchHotResponse.HotItemsBean, BaseViewHolder>(
        R.layout.item_tag_hot_list,
        list
    ) {
    override fun convert(
        helper: BaseViewHolder,
        item: SearchHotResponse.HotItemsBean?
    ) {
        helper.apply {
            item?.also {
                setText(R.id.tv_title, it.name)
                addOnClickListener(R.id.tv_title)
            }
        }

    }
}