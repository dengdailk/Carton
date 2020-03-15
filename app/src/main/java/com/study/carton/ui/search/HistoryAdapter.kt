package com.study.carton.ui.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.carton.R
import com.study.carton.db.HistoryRecord


class HistoryAdapter(list: List<HistoryRecord>) :
    BaseQuickAdapter<HistoryRecord, BaseViewHolder>(
        R.layout.item_search_hisroty,
        list
    ) {
    override fun convert(
        helper: BaseViewHolder,
        item: HistoryRecord?
    ) {
        helper.apply {
            item?.also {
                setText(R.id.tv_title, it.name)
                addOnClickListener(R.id.iv_delete)
                addOnClickListener(R.id.tv_title)
            }
        }

    }
}