package com.study.carton.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * @author dengdai
 * @date 2020/3/10.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
@Entity
data class HistoryRecord(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo
    var comicId: String,
    @ColumnInfo
    var name: String,
    @ColumnInfo
    var time: String
)