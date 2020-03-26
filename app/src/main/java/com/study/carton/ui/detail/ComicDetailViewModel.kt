package com.study.carton.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.study.carton.base.BaseViewModel
import com.study.carton.bean.detail.ComicDetailResponse
import com.study.carton.db.*
import com.study.carton.http.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ComicDetailViewModel : BaseViewModel() {

    var mComicDetailResponse = MutableLiveData<ComicDetailResponse>()

    var mSaveReadChapter = MutableLiveData<ComicDetailResponse.ChapterListBean?>()

    var mLastChapterBean = MutableLiveData<ComicDetailResponse.ChapterListBean?>()
    //收藏 true 已收藏 false 收藏
    var mSaveCollection = MutableLiveData<Boolean>()

    private val comicCollectionDao : ComicCollectionDao = BookDatabase.instance.getComicCollectionDao()
    private val historyRecordDao : HistoryRecordDao = BookDatabase.instance.getHistoryRecordDao()
    private val readChapterDao:ReadChapterDao = BookDatabase.instance.getReadChapterDao()

    fun getComicDetail(comicId: String) {
        request({
            val detailResponse = RetrofitClient.service.comicDetail(comicId)
            val returnData = detailResponse.data?.returnData
            returnData?.apply {
//                val db = mBookDao.finReadChapterList(comic.comic_id)
                val db = readChapterDao.getAllReadChapter()
                if (db.isNotEmpty())
                    chapter_list = chapter_list?.let { setIsRead(it, db) }
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
                readChapterDao.getReadChapter(comicId)
        }
    }

    fun saveAndCancelCollection(comicBean: ComicDetailResponse.ComicBean,size:Int,readChapter:Int = -1) {
        viewModelScope.launch {
            mSaveCollection.value = withContext(Dispatchers.IO){
                val findCollection = comicCollectionDao.getComicCollection(comicBean.comic_id)
                if (findCollection == null){
                    comicCollectionDao.insert(ComicCollection(0,comicBean.comic_id,comicBean.name,comicBean.cover,size,readChapter))
                    return@withContext true
                }else{
                    comicCollectionDao.delete(comicBean.comic_id)
                    return@withContext false
                }
            }
        }
    }


    fun getCollectionStatus(comicBean: ComicDetailResponse.ComicBean) {
        viewModelScope.launch {
            comicBean.comic_id.let { comicCollectionDao.getComicCollection(it) }
        }
    }


    /**
     * 保存章节阅读记录
     */
    @Suppress("LABEL_NAME_CLASH")
    fun saveReadChapter(
        book: ComicDetailResponse.ComicBean,
        chapterListBean: ComicDetailResponse.ChapterListBean
    ) {
        viewModelScope.launch {
            mSaveReadChapter.value = withContext(Dispatchers.IO) {
                val chapter = chapterListBean.chapter_id?.let { readChapterDao.getReadChapter(it) }
                if (chapter == null){
                    chapterListBean.chapter_id?.let {
                        chapterListBean.name?.let { it1 ->
                            chapterListBean.type?.let { it2 ->
                                ReadChapter(0,
                                    book.comic_id, book.name, it
                                    , it1, it2,0
                                )
                            }
                        }
                    }?.let { readChapterDao.insert(it) }
                }else{
                    chapter.comicId = book.comic_id
                    chapter.comicName = book.name
                    chapter.chapterName = chapterListBean.name!!
                    chapter.type = chapterListBean.type!!
                    readChapterDao.update(chapter)
                }
                chapterListBean
            }
        }
    }


    /**
     * 保存历史记录
     */
    fun saveHistoryRecord(book: ComicDetailResponse.ComicBean){
        viewModelScope.launch(Dispatchers.IO){
            val historyRecord = HistoryRecord(0,
                book.comic_id, book.name,book.last_update_time.toString())
            historyRecord.let { historyRecordDao.insert(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Logger.e("-- onCleared")
    }

}