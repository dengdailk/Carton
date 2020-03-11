package com.study.carton.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.study.carton.base.BaseViewModel
import com.study.carton.bean.home.HomeTypeInfo
import com.study.carton.bean.home.RecommendResponse
import com.study.carton.http.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : BaseViewModel() {
    var mRecommendResponse = MutableLiveData<RecommendResponse>()
    val mHomeList = MutableLiveData<List<HomeTypeInfo>>()
    fun getRecommend() {
        request({
            RetrofitClient.service.boutiqueList()
        }, {
            mRecommendResponse.value = it.returnData
            it.returnData?.let { it1 -> handlerHomeInfo(it1) }
        })
    }
    //处理数据

    private fun handlerHomeInfo(response: RecommendResponse){
        val list = ArrayList<HomeTypeInfo>()
        viewModelScope.launch(Dispatchers.Main) {
            //获取数据
            mHomeList.value = withContext(Dispatchers.IO) {
                response.comicLists?.forEach {

                    val comicListsBean = it
                    val homeInfo = HomeTypeInfo(HomeTypeInfo.TITLE)
                    homeInfo.setTitleInfo(comicListsBean)
                    list.add(homeInfo)

                    when {
                        //头部
                        it.comics!!.size > 6 -> {
                            val content1 = HomeTypeInfo(HomeTypeInfo.HEARD_LIST)
                            content1.comicList = comicListsBean.comics
                            list.add(content1)
                        }
                        //六宫格
                        it.comics!!.size == 6 -> for (index in it.comics!!.indices) {
                            val content1 = HomeTypeInfo(HomeTypeInfo.CONTENT_1)
                            content1.listIndex = index
                            content1.comic = it.comics!![index]
                            list.add(content1)
                        }
                        //专题广告
                        it.comics!!.size==1 -> {
                            val content1 = HomeTypeInfo(HomeTypeInfo.BANNER)
                            content1.comic = it.comics!![0]
                            list.add(content1)
                        }
                        //详细介绍 4 宫格
                        it.comics!!.size == 4 -> for (index in it.comics!!.indices) {
                            val content1 = HomeTypeInfo(HomeTypeInfo.CONTENT_2)
                            content1.listIndex = index
                            content1.comic = it.comics?.get(index)
                            list.add(content1)
                        }
                    }
                }

                return@withContext list
            }
        }
    }

}
