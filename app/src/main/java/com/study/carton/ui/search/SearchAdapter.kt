package com.study.carton.ui.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.carton.R
import com.study.carton.bean.search.ComicSearchResponse
import com.study.carton.utils.GlideUtils

class SearchAdapter(data: List<ComicSearchResponse.ComicsBean>) :
    BaseQuickAdapter<ComicSearchResponse.ComicsBean, BaseViewHolder>(
        R.layout.item_search_list,
        data
    ) {

    override fun convert(helper: BaseViewHolder, item: ComicSearchResponse.ComicsBean?) {
        helper.apply {
            item?.also {
                GlideUtils.loadImage(mContext, it.cover, getView(R.id.iv_cover), 0f)
                setText(R.id.tv_chapter, "共:"+it.passChapterNum)
                    .setText(R.id.tv_author, it.author)
                    .setText(R.id.tv_des_content, it.description)
                    .setText(R.id.tv_title, it.name)
                    .addOnClickListener(R.id.cl_layout)
            }
        }
    }
}