package com.androidui.fragment.case3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.androidui.R
import com.androidui.fragment.case1.TestFragment
import com.androidui.fragment.case2.Case2DetailFragment
import com.androidui.fragment.case2.Case2ListFragment
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.fragment.FragTabEngine
import com.kotlinlib.fragment.FragUtils
import com.kotlinlib.fragment.FragmentUtils
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_test.*

@LayoutId(R.layout.activity_test)
class Case3Activity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        val fte = FragTabEngine(this, tl, R.id.flContainer,
        {
            i, tab ->
            tab.setText("frag${i+1}")
        },
        {
            fu, frags, tab ->
            tab.position.toast()
            fu.switch(tab.position)
        },
        {

        },
        Case3Fragment().apply { arguments=Bundle().apply { putString("text","frag1") } },
        TestFragment(),
        Case2ListFragment(),
        Case2DetailFragment())

    }
}


