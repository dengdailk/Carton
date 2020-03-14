package com.study.carton.db

import androidx.room.*

/**
 * @author dengdai
 * @date 2020/3/11.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
@Dao
interface ReadChapterDao:BaseDao<ReadChapter> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(element: ReadChapter)

    @Query("select * from ReadChapter")
    fun getAllReadChapter():MutableList<ReadChapter>

    @Query("select * from ReadChapter where chapter_id = :chapter_id")
    fun getReadChapter(chapter_id:String):ReadChapter

    @Query("select * from ReadChapter order by chapter_id desc ")
    fun getAllByDataDesc():MutableList<ReadChapter>

    @Query("select * from ReadChapter order by chapter_id desc limit 1")
    fun getByDataDescTop():MutableList<ReadChapter>

    @Query("delete from ReadChapter")
    fun deleteAll()

    @Update
    fun saveReadPosition(chapter:ReadChapter)
}