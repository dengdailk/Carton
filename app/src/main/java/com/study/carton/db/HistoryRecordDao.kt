package com.study.carton.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author dengdai
 * @date 2020/3/11.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
@Dao
interface HistoryRecordDao:BaseDao<HistoryRecord> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(element: HistoryRecord)

    @Query("select * from HistoryRecord")
    fun getAllHistoryRecord():MutableList<HistoryRecord>

    @Query("select * from HistoryRecord where comicId = :comicId")
    fun getHistoryRecord(comicId:String):HistoryRecord

    @Query("select * from HistoryRecord order by comicId desc ")
    fun getAllByDateDesc():MutableList<HistoryRecord>

    @Query("delete from HistoryRecord")
    fun deleteAll()
}