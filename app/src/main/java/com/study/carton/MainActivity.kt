package com.study.carton

import android.os.Bundle
import com.iammert.library.readablebottombar.ReadableBottomBar
import com.orhanobut.logger.Logger
import com.study.carton.base.BaseActivity
import com.study.carton.ui.bookshelf.BookShelfFragment
import com.study.carton.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import me.yokeyword.fragmentation.SupportFragment

class MainActivity : BaseActivity() {
    private val mFragments = arrayOfNulls<SupportFragment>(2)

    private var prePosition = 0

    override fun getLayout(): Int = R.layout.activity_main

    override fun init(savedInstanceState: Bundle?) {
        val firstFragment = findFragment(HomeFragment::class.java)

        if (firstFragment == null) {
            mFragments[0] = HomeFragment()
            mFragments[1] = BookShelfFragment()
            loadMultipleRootFragment(
                R.id.fl_main, 0, mFragments[0],
                mFragments[1]
            )
        } else {
            // 这里我们需要拿到mFragments的引用
            mFragments[0] = firstFragment
            mFragments[1] = findFragment(BookShelfFragment::class.java)
        }

        readableBottomBar.setOnItemSelectListener(object : ReadableBottomBar.ItemSelectListener {
            override fun onItemSelected(index: Int) {
                if (index == prePosition)
                    return
                showHideFragment(mFragments[index], mFragments[prePosition])
                prePosition = index
                Logger.e("-- onItemSelected $index")
            }
        })
    }

}
