package com.study.carton.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.study.carton.base.BaseViewModel
import com.study.carton.bean.search.ComicSearchResponse
import com.study.carton.bean.search.SearchHotResponse
import com.study.carton.db.BookDatabase
import com.study.carton.db.HistoryRecord
import com.study.carton.http.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : BaseViewModel() {

    val mSearchBean = MutableLiveData<SearchHotResponse>()
    val mHistoryBean = MutableLiveData<List<HistoryRecord>>()

    val mSearchResult = MutableLiveData<ComicSearchResponse>()

    private val historyRecord = BookDatabase.instance.getHistoryRecordDao()

    fun getSearchHot() {
        request({
            RetrofitClient.service.comicSearchHot()
        }, {
            mSearchBean.value = it.returnData
        })
    }

    fun getHistory() {
        viewModelScope.launch {
            mHistoryBean.value = withContext(Dispatchers.IO) {
                return@withContext historyRecord.getAllHistoryRecord()
            }
        }
    }

    fun getSearchResult(str: String, pager: Int) {
        request({
            RetrofitClient.service.comicSearch(str, pager)
        }, {
            mSearchResult.value = it.returnData
        })
    }

    fun deleteHistoryRecord(comicId: String){
        return historyRecord.delete(comicId)
    }
    fun deleteAllHistoryRecord() {
        historyRecord.deleteAll()
    }

}