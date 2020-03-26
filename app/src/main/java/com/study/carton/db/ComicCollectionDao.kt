package com.study.carton.db

import androidx.room.*

/**
 * @author dengdai
 * @date 2020/3/10.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
@Dao
interface ComicCollectionDao:BaseDao<ComicCollection> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(element: ComicCollection)

    @Query("select * from comicCollection")
    fun getAllComicCollection():MutableList<ComicCollection>

    @Query("select * from ComicCollection where comicId = :comicId")
    fun getComicCollection(comicId:String):ComicCollection

    @Query("select * from ComicCollection order by comicId desc ")
    fun getAllByDateDesc():MutableList<ComicCollection>

    @Query("delete from ComicCollection")
    fun deleteAll()

    @Query("delete from ComicCollection where comicId = :comicId")
    fun delete(comicId: String)

}