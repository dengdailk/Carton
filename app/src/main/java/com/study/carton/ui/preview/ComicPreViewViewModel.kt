package com.study.carton.ui.preview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.study.carton.base.BaseViewModel
import com.study.carton.bean.detail.ComicDetailResponse
import com.study.carton.bean.preview.ComicPreViewResponse
import com.study.carton.db.*
import com.study.carton.http.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.ParsePosition

/**
 *
 * @author  Lai
 *
 * @time 2019/10/4 14:51
 * @describe describe
 *
 */
class ComicPreViewViewModel : BaseViewModel() {
    val mPerViewResponse = MutableLiveData<ComicPreViewResponse>()
    val comicCollectionDao : ComicCollectionDao = BookDatabase.instance.getComicCollectionDao()
    val historyRecordDao : HistoryRecordDao = BookDatabase.instance.getHistoryRecordDao()
    val chapterDao = BookDatabase.instance.getReadChapterDao()
    //当前最新章节信息
    private var mCurrRequestNewChapterBean: ComicDetailResponse.ChapterListBean? = null


    fun setCurrChapterInfo(currRequestNewChapterBean: ComicDetailResponse.ChapterListBean) {
        this.mCurrRequestNewChapterBean = currRequestNewChapterBean
    }


    fun getPreView(chapterId: String) {
        request({
            val comicPreView = RetrofitClient.service.comicPreView(chapterId)
            mCurrRequestNewChapterBean?.also {
                val imageList = comicPreView.data?.returnData?.image_list
                imageList?.forEachIndexed { index, imageListBean ->
                    imageListBean.chapterName = it.name
                    imageListBean.listIndex = index + 1
                    imageListBean.listSize = imageList.size
                    imageListBean.chapter_id = it.chapter_id
                }
//                mBookDao.finReadChapterById(it.chapter_id)?.apply {
//                    comicPreView.data?.returnData?.position = this.readPosition
//                }
                chapterDao.getReadChapter(it.chapter_id).apply {
                    comicPreView.data?.returnData?.position = this.readPosition
                }
            }
            comicPreView
        }, {
            mPerViewResponse.value = it.returnData
        })
    }

    fun saveReadChapter(
        comicId: String?,
        comicName: String?,
        chapter_id: String,
        chapterName: String,
        type: String,
        readPosition: Int
    ) {
        if (comicId != null && comicName != null) {
            return chapterDao.insert(
                ReadChapter(
                    0,
                    chapter_id,
                    chapterName,
                    comicId,
                    comicName,
                    type,
                    readPosition
                )
            )
        }
    }


    fun saveReadPosition(chapter_id: String, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
//                      val status =   mBookDao.saveReadPosition(chapter_id, position)
            saveReadPosition(chapter_id, position)
//            com.orhanobut.logger.Logger.e("-- 保存阅读位置 $position status $status")
        }
    }

}