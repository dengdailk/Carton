package com.study.carton.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.study.carton.bean.detail.ComicDetailResponse

/**
 * @author dengdai
 * @date 2020/3/10.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
@Entity
data class ComicCollection (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo
    var comicId:String,
    @ColumnInfo
    var comicName:String,
    @ColumnInfo
    var coverUrl:String,
    @ColumnInfo
    var comicSize:Int,
    @ColumnInfo
    var readChapterPosition:Int
)