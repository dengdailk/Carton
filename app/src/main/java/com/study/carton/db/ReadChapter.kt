package com.study.carton.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author dengdai
 * @date 2020/3/10.
 * GitHub：
 * email：291996307@qq.com
 * description：
 */
@Entity
data class ReadChapter(
    @PrimaryKey(autoGenerate = false)
    var id:Int,
    @ColumnInfo
    //对应章节ID和名字
    var chapter_id: String,
    @ColumnInfo
    var chapterName: String,
//对应书的ID和名字
    @ColumnInfo
    var comicId: String,
    @ColumnInfo
    var comicName: String,
    @ColumnInfo
    var type: String,
//第几页
    @ColumnInfo
    var readPosition: Int
)