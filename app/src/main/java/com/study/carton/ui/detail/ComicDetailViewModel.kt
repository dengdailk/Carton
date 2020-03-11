package com.study.carton.ui.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.study.carton.base.BaseViewModel
import com.study.carton.bean.detail.ComicDetailResponse
import com.study.carton.db.*
import com.study.carton.http.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *
 * @author  Lai
 *
 * @time 2019/10/3 21:16
 * @describe describe
 *
 */
class ComicDetailViewModel : BaseViewModel() {

    var mComicDetailResponse = MutableLiveData<ComicDetailResponse>()

    var mSaveReadChapter = MutableLiveData<ComicDetailResponse.ChapterListBean?>()

    var mLastChapterBean = MutableLiveData<ComicDetailResponse.ChapterListBean?>()
    //收藏 true 已收藏 false 收藏
    var mSaveCollection = MutableLiveData<Boolean>()

    val comicCollectionDao : ComicCollectionDao = BookDatabase.instance.getComicCollectionDao()
    val historyRecordDao : HistoryRecordDao = BookDatabase.instance.getHistoryRecordDao()
    val readChapterDao:ReadChapterDao = BookDatabase.instance.getReadChapterDao()

    fun getComicDetail(comicId: String) {
        request({
            val detailResponse = RetrofitClient.service.comicDetail(comicId)
            val returnData = detailResponse.data?.returnData
            returnData?.apply {
//                val db = mBookDao.finReadChapterList(comic.comic_id)
                val db = readChapterDao.getAllReadChapter()
                if (db.isNotEmpty())
                    chapter_list = setIsRead(chapter_list, db)
            }
            detailResponse
        }, {
            mComicDetailResponse.value = it.returnData
        })
    }


    private fun setIsRead(
        requestList: List<ComicDetailResponse.ChapterListBean>,
        dbList: List<*>
    ): List<ComicDetailResponse.ChapterListBean> {
        for (bean in requestList) {
            val indexOf = dbList.indexOf(bean)
            if (indexOf != -1) {
                val dbInfo = dbList[indexOf]
                if (dbInfo is ReadChapter) {
                    bean.isRead = true
                }
            }
        }
        return requestList
    }


    fun getLastChapter(comicId: String) {
        viewModelScope.launch {
                readChapterDao.getByDataDescTop()
        }
    }

    fun saveAndCancelCollection(comicBean: ComicDetailResponse.ComicBean,size:Int,readChapter:Int = -1) {
        viewModelScope.launch {
            comicCollectionDao.delete(comicBean.comic_id)
        }
    }


    fun getCollectionStatus(comicBean: ComicDetailResponse.ComicBean) {
        viewModelScope.launch {
            comicCollectionDao.getComicCollection(comicBean.comic_id)
        }
    }


    /**
     * 保存章节阅读记录
     */
    fun saveReadChapter(
        book: ComicDetailResponse.ComicBean,
        chapterListBean: ComicDetailResponse.ChapterListBean
    ) {
        viewModelScope.launch {
//            mSaveReadChapter.value = withContext(Dispatchers.IO) {
//                if (!mBookDao.saveReadChapter(
//                        book.comic_id, book.name, chapterListBean.chapter_id
//                        , chapterListBean.name, chapterListBean.type
//                    )
//                ) {
//                    return@withContext null
//                }
//                chapterListBean
//            }
//            readChapterDao.insert(book)
        }
    }


    /**
     * 保存历史记录
     */
    fun saveHistoryRecord(book: ComicDetailResponse.ComicBean){
        viewModelScope.launch(Dispatchers.IO){
//            historyRecordDao.insert(book)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Logger.e("-- onCleared")
    }

}