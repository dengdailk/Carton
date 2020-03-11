package com.study.carton.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.study.carton.App

/**
 * @author dengdai
 * @date 2020/3/10.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
@Database(entities = [ComicCollection::class,HistoryRecord::class,ReadChapter::class],version = 1)
abstract class BookDatabase : RoomDatabase() {
    abstract fun getComicCollectionDao():ComicCollectionDao
    abstract fun getHistoryRecordDao():HistoryRecordDao
    abstract fun getReadChapterDao():ReadChapterDao

    companion object{
        val instance = Single.sin
    }

    private object Single{
        val sin:BookDatabase = Room.databaseBuilder(
            App.CONTEXT,
            BookDatabase::class.java,
            "Book.db"
        )
            .allowMainThreadQueries()
            .build()
    }
}