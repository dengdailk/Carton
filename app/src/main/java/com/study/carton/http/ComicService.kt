package com.study.carton.http

import com.study.carton.bean.comm.ComicResponse
import com.study.carton.bean.detail.ComicDetailResponse
import com.study.carton.bean.home.RecommendResponse
import com.study.carton.bean.preview.ComicPreViewResponse
import com.study.carton.bean.search.ComicSearchResponse
import com.study.carton.bean.search.SearchHotResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ComicService {
    companion object {
        const val BASE_URL = "http://app.u17.com/v3/appV3_3/android/phone/"
    }

    @GET("comic/boutiqueListNew")
    suspend fun boutiqueList(@Query("sexType") sexType: Int = 1): ComicResponse<RecommendResponse>

    @GET("comic/detail_static_new")
    suspend fun comicDetail(@Query("comicid") comicId: String): ComicResponse<ComicDetailResponse>

    @GET("comic/chapterNew")
    suspend fun comicPreView(@Query("chapter_id") chapter_id: String): ComicResponse<ComicPreViewResponse>

    @GET("search/searchResult")
    suspend fun comicSearch(@Query("q") text: String, @Query("page") page: Int): ComicResponse<ComicSearchResponse>

    @GET("search/hotkeywordsnew")
    suspend fun comicSearchHot(): ComicResponse<SearchHotResponse>

}