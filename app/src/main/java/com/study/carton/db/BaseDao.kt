package com.study.carton.db

import androidx.room.*
import com.google.android.material.bottomsheet.BottomSheetBehavior

/**
 * @author dengdai
 * @date 2020/3/10.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list:MutableList<T>)

    @Delete
    fun delete(element: T)
    @Delete
    fun deleteList(elements:MutableList<T>)
    @Delete
    fun deleteSome(vararg elements:T)

    @Update
    fun update(element: T)
}