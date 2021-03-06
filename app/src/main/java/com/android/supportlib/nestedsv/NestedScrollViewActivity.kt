package com.android.supportlib.nestedsv

import android.os.Bundle
import com.android.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_nested_scroll_view.*

@LayoutId(R.layout.activity_nested_scroll_view)
class NestedScrollViewActivity : KotlinActivity() {
    override fun init(bundle: Bundle?) {
        btnView1.click {
            go(NestedSVCase1Activity::class.java)
        }
    }
}
