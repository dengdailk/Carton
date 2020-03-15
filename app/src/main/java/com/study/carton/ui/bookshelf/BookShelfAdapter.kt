package com.study.carton.ui.bookshelf

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.study.carton.R
import com.study.carton.db.ComicCollection
import com.study.carton.utils.GlideUtils


class BookShelfAdapter(data:List<ComicCollection>) :
    BaseQuickAdapter<ComicCollection,BaseViewHolder>(R.layout.item_book_shelf_item,data){

    override fun convert(helper: BaseViewHolder, item: ComicCollection?) {
        helper.apply {
            item?.also {
                setText(R.id.tv_title, it.comicName)
                addOnClickListener(R.id.iv_cover)
                GlideUtils.loadImage(mContext, it.coverUrl, getView(R.id.iv_cover),
                    0f)
            }
        }
    }

}