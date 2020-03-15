package com.study.carton.ui.bookshelf

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.study.carton.base.BaseViewModel
import com.study.carton.db.BookDatabase
import com.study.carton.db.ComicCollection
import kotlinx.coroutines.launch

class BookShelfViewModel : BaseViewModel() {

    val mCollection = MutableLiveData<List<ComicCollection>>()
    private val collectionDao = BookDatabase.instance.getComicCollectionDao()
    fun getAllCollection(){
        viewModelScope.launch {
            mCollection.postValue(collectionDao.getAllComicCollection())
        }
    }
}
