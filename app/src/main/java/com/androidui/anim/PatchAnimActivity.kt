package com.androidui.anim

import android.os.Bundle
import android.view.animation.*
import com.androidui.R
import com.kotlinlib.activity.KotlinActivity
import com.kotlinlib.other.LayoutId
import kotlinx.android.synthetic.main.activity_patch_anim.*
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation
import android.view.animation.RotateAnimation
import android.view.animation.AnimationSet



@LayoutId(R.layout.activity_patch_anim)
class PatchAnimActivity : KotlinActivity() {

    override fun init(bundle: Bundle?) {
        tvTop.text = """
1. 作用对象
视图控件（View）
如Android的TextView、Button等等
不可作用于View组件的属性，如：颜色、背景、长度等等

2. 原理
通过确定开始的视图样式 & 结束的视图样式、中间动画变化过程由系统补全来确定一个动画
结束的视图样式：平移、缩放、旋转 & 透明度样式
即补间动画的动画效果就是：平移、缩放、旋转 & 透明度动画

3. 分类
根据不同的动画效果，补间动画分为4种动画：
平移动画（Translate）
缩放动画（scale）
旋转动画（rotate）
透明度动画（alpha）

4. 具体使用
补间动画的使用方式分为两种：在XML 代码 / Java 代码里设置
前者优点：动画描述的可读性更好
后者优点：动画效果可动态创建
        """.trimIndent()

        header1.setLeftClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
// 采用<translate /> 标签表示平移动画
<translate xmlns:android="http://schemas.android.com/apk/res/android"

    // 以下参数是4种动画效果的公共属性,即都有的属性

    // 动画持续时间（ms），必须设置，动画才有效果
    android:duration="3000"
    // 动画延迟开始时间（ms）
    android:startOffset ="1000"
    // 动画播放完后，视图是否会停留在动画开始的状态，默认为true
    android:fillBefore = “true”
    // 动画播放完后，视图是否会停留在动画结束的状态，优先于fillBefore值，默认为false
    android:fillAfter = “false”
    // 是否应用fillBefore值，对fillAfter值无影响，默认为true
    android:fillEnabled= “true”
    // 选择重复播放动画模式，restart代表正序重放，reverse代表倒序回放，默认为restart
    android:repeatMode= “restart”
    // 重放次数（所以动画的播放次数=重放次数+1），为infinite时无限重复
    android:repeatCount = “0”
    // 插值器，即影响动画的播放速度,下面会详细讲
    android:interpolator = @[package:]anim/interpolator_resource

    // 以下参数是平移动画特有的属性

    // 视图在水平方向x 移动的起始值
    android:fromXDelta="0"
    // 视图在水平方向x 移动的结束值
    android:toXDelta="500"
    // 视图在竖直方向y 移动的起始值
    android:fromYDelta="0"
    // 视图在竖直方向y 移动的结束值
    android:toYDelta="500"

/>             """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
<translate xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="3000"
    android:startOffset="1000"
    android:fillBefore = "true"
    android:fillAfter = "false"
    android:fromXDelta="0"
    android:toXDelta="500"
    android:fromYDelta="0"
    android:toYDelta="0"
    />

val anim = AnimationUtils.loadAnimation(this, R.anim.translate)
btn1.click {
    iv1.startAnimation(anim)
}
            """.trimIndent())
        }

        val anim = AnimationUtils.loadAnimation(this, R.anim.translate)
        btn1.click {
            iv1.startAnimation(anim)
        }

        header2.setRightClick {
            codeDialog.text("""
val transAnim = TranslateAnimation(0f,500f,0f,0f)
transAnim.duration = 3000
btn2.click {
    iv2.startAnimation(transAnim)
}
            """.trimIndent())
        }

        val transAnim = TranslateAnimation(0f,500f,0f,0f)
        transAnim.duration = 3000
        btn2.click {
            iv2.startAnimation(transAnim)
        }

        header3.setLeftClick {
            codeDialog.text("""
// 以下参数是缩放动画特有的属性

// 动画在水平方向X的起始缩放倍数
// 0.0表示收缩到没有；1.0表示正常无伸缩
// 值小于1.0表示收缩；值大于1.0表示放大
android:fromXScale="0.0"
// 动画在水平方向X的结束缩放倍数
android:toXScale="2"

// 动画开始前在竖直方向Y的起始缩放倍数
android:fromYScale="0.0"
// 动画在竖直方向Y的结束缩放倍数
android:toYScale="2"

// 缩放轴点的x坐标
android:pivotX="50%"
// 缩放轴点的y坐标
android:pivotY="50%"
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
<scale xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromXScale="0"
    android:toXScale="2"
    android:fromYScale="0"
    android:toYScale="2"
    android:pivotX="50%"
    android:pivotY="50%">
</scale>

btn3.click {
    iv3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale).apply {
        duration = 2000
        repeatCount = 1
    })
}
            """.trimIndent())
        }

        btn3.click {
            iv3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale).apply {
                duration = 2000
                repeatCount = 1
            })
        }

        header4.setRightClick {
            codeDialog.text("""
val scaleAnim = ScaleAnimation(0f,2f,0f,2f,
        Animation.RELATIVE_TO_SELF,0.5f,
        Animation.RELATIVE_TO_SELF,0.5f)

btn4.click {
    iv4.startAnimation(scaleAnim.apply {
        duration = 2000
        repeatCount = 1
    })
}
            """.trimIndent())
        }

        val scaleAnim = ScaleAnimation(0f,2f,0f,2f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f)

        btn4.click {
            iv4.startAnimation(scaleAnim.apply {
                duration = 2000
                repeatCount = 1
            })
        }

        header5.setLeftClick {
            codeDialog.text("""
// 以下参数是旋转动画特有的属性

android:duration="1000"
// 动画开始时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
android:fromDegrees="0"
// 动画结束时 视图的旋转角度(正数 = 顺时针，负数 = 逆时针)
android:toDegrees="270"
// 旋转轴点的x坐标
android:pivotX="50%"
// 旋转轴点的y坐标
android:pivotY="0"
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
btn5.click {
    iv5.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate).apply {
        duration = 2000
        repeatCount = 1
    })
}
            """.trimIndent())
        }

        btn5.click {
            iv5.startAnimation(AnimationUtils.loadAnimation(this, R.anim.rotate).apply {
                duration = 2000
                repeatCount = 1
            })
        }

        header6.setRightClick {
            codeDialog.text("""
val rotateAnim = RotateAnimation(0f,270f,
        Animation.RELATIVE_TO_SELF,0.5f,
        Animation.RELATIVE_TO_SELF,0.5f)

btn6.click {
    iv6.startAnimation(rotateAnim.apply {
        duration = 2000
        repeatCount = 1
    })
}
            """.trimIndent())
        }

        val rotateAnim = RotateAnimation(0f,270f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f)

        btn6.click {
            iv6.startAnimation(rotateAnim.apply {
                duration = 2000
                repeatCount = 1
            })
        }

        header7.setLeftClick {
            codeDialog.text("""
// 以下参数是透明度动画特有的属性

// 动画开始时视图的透明度(取值范围: -1 ~ 1)
android:fromAlpha="1.0"
// 动画结束时视图的透明度(取值范围: -1 ~ 1)
android:toAlpha="0.0"
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
<alpha xmlns:android="http://schemas.android.com/apk/res/android"
    android:fromAlpha="1"
    android:toAlpha="0.1"
    />

iv7.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha).apply {
    duration = 2000
    repeatCount = 1
})
            """.trimIndent())
        }

        btn7.click {
            iv7.startAnimation(AnimationUtils.loadAnimation(this, R.anim.alpha).apply {
                duration = 2000
                repeatCount = 1
            })
        }

        header8.setRightClick {
            codeDialog.text("""
val alphaAnim = AlphaAnimation(1f,0.1f)
btn8.click {
    iv8.startAnimation(alphaAnim.apply {
        duration = 2000
        repeatCount = 1
    })
}
            """.trimIndent())
        }

        val alphaAnim = AlphaAnimation(1f,0.1f)
        btn8.click {
            iv8.startAnimation(alphaAnim.apply {
                duration = 2000
                repeatCount = 1
            })
        }

        header9.setLeftClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
// 采用< Set/>标签
<set xmlns:android="http://schemas.android.com/apk/res/android">

// 组合动画同样具备公共属性
    // 动画持续时间（ms），必须设置，动画才有效果
    android:duration="3000"
    // 动画延迟开始时间（ms）
    android:startOffset ="1000"
    // 动画播放完后，视图是否会停留在动画开始的状态，默认为true
    android:fillBefore = “true”
    // 动画播放完后，视图是否会停留在动画结束的状态，优先于fillBefore值，默认为false
    android:fillAfter = “false”
    // 是否应用fillBefore值，对fillAfter值无影响，默认为true
    android:fillEnabled= “true”
    // 选择重复播放动画模式，restart代表正序重放，reverse代表倒序回放，默认为restart
    android:repeatMode= “restart”
    // 重放次数（所以动画的播放次数=重放次数+1），为infinite时无限重复
    android:repeatCount = “0”
    // 插值器，即影响动画的播放速度,下面会详细讲
    android:interpolator = @[package:]anim/interpolator_resource

// 组合动画独特的属性
    // 表示组合动画中的动画是否和集合共享同一个差值器
    // 如果集合不指定插值器，那么子动画需要单独设置
    android:shareinterpolator = “true”


// 组合动画播放时是全部动画同时开始
// 如果想不同动画不同时间开始就要使用android:startOffset属性来延迟单个动画播放时间

// 设置旋转动画，语法同单个动画
    <rotate
        android:duration="1000"
        android:fromDegrees="0"
        android:toDegrees="360"
        android:pivotX="50%"
        android:pivotY="50%"
        android:repeatMode="restart"
        android:repeatCount="infinite"
        />

// 设置平移动画，语法同单个动画
    <translate
        android:duration="10000"
        android:startOffset = “1000”// 延迟该动画播放时间
        android:fromXDelta="-50%p"
        android:fromYDelta="0"
        android:toXDelta="50%p"
        android:toYDelta="0" />

// 设置透明度动画，语法同单个动画
    <alpha
        android:startOffset="7000"
        android:duration="3000"
        android:fromAlpha="1.0"
        android:toAlpha="0.0" />


// 设置缩放动画，语法同单个动画
    <scale
        android:startOffset="4000"
        android:duration="1000"
        android:fromXScale="1.0"
        android:fromYScale="1.0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toXScale="0.5"
        android:toYScale="0.5" />
// 特别注意：
// 1. 在组合动画里scale缩放动画设置的repeatCount（重复播放）和fillBefore（播放完后，视图是否会停留在动画开始的状态）是无效的。
// 2. 所以如果需要重复播放或者回到原位的话需要在set标签里设置
// 3. 但是由于此处rotate旋转动画里已设置repeatCount为infinite，所以动画不会结束，也就看不到重播和回复原位

</set>
            """.trimIndent())
        }.setRightClick {
            codeDialog.text("""
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android"
    android:duration="3000"
    android:startOffset ="1000"
    android:fillBefore = "true"
    android:fillAfter = "false"
    android:fillEnabled= "true"
    android:repeatMode= "restart"
    android:repeatCount = "0"
    android:shareInterpolator="true">

    <rotate
        android:duration="1000"
        android:fromDegrees="0"
        android:toDegrees="360"
        android:pivotX="50%"
        android:pivotY="50%"
        android:repeatMode="restart"
        android:repeatCount="infinite"
        />

    <translate
        android:duration="10000"
        android:startOffset = "1000"
        android:fromXDelta="-50%p"
        android:fromYDelta="0"
        android:toXDelta="50%p"
        android:toYDelta="0" />

    <alpha
        android:startOffset="7000"
        android:duration="3000"
        android:fromAlpha="1.0"
        android:toAlpha="0.0" />

    <scale
        android:startOffset="4000"
        android:duration="1000"
        android:fromXScale="1.0"
        android:fromYScale="1.0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toXScale="0.5"
        android:toYScale="0.5" />
</set>

btn9.click {
    iv9.startAnimation(AnimationUtils.loadAnimation(this, R.anim.set))
}
            """.trimIndent())
        }

        btn9.click {
            iv9.startAnimation(AnimationUtils.loadAnimation(this, R.anim.set))
        }

        header10.setRightClick {
            codeDialog.text("""
// 组合动画设置

// 步骤1:创建组合动画对象(设置为true)
val setAnimation = AnimationSet(true)

// 步骤2:设置组合动画的属性
// 特别说明以下情况
// 因为在下面的旋转动画设置了无限循环(RepeatCount = INFINITE)
// 所以动画不会结束，而是无限循环
// 所以组合动画的下面两行设置是无效的
setAnimation.repeatMode = Animation.RESTART
setAnimation.repeatCount = 1// 设置了循环一次,但无效

// 步骤3:逐个创建子动画(方式同单个动画创建方式,此处不作过多描述)

// 子动画1:旋转动画
val rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
rotate.duration = 1000
rotate.repeatMode = Animation.RESTART
rotate.repeatCount = Animation.INFINITE

// 子动画2:平移动画
val translate = TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, -0.5f,
        TranslateAnimation.RELATIVE_TO_PARENT, 0.5f,
        TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f)
translate.duration = 10000

// 子动画3:透明度动画
val alpha = AlphaAnimation(1f, 0f)
alpha.duration = 3000
alpha.startOffset = 7000

// 子动画4:缩放动画
val scale1 = ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
scale1.duration = 1000
scale1.startOffset = 4000

// 步骤4:将创建的子动画添加到组合动画里
setAnimation.addAnimation(alpha)
setAnimation.addAnimation(rotate)
setAnimation.addAnimation(translate)
setAnimation.addAnimation(scale1)

btn10.click {
    iv10.startAnimation(setAnimation)
}
            """.trimIndent())
        }

        // 组合动画设置

        // 步骤1:创建组合动画对象(设置为true)
        val setAnimation = AnimationSet(true)

        // 步骤2:设置组合动画的属性
        // 特别说明以下情况
        // 因为在下面的旋转动画设置了无限循环(RepeatCount = INFINITE)
        // 所以动画不会结束，而是无限循环
        // 所以组合动画的下面两行设置是无效的
        setAnimation.repeatMode = Animation.RESTART
        setAnimation.repeatCount = 1// 设置了循环一次,但无效

        // 步骤3:逐个创建子动画(方式同单个动画创建方式,此处不作过多描述)

        // 子动画1:旋转动画
        val rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 1000
        rotate.repeatMode = Animation.RESTART
        rotate.repeatCount = Animation.INFINITE

        // 子动画2:平移动画
        val translate = TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, -0.5f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.5f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f)
        translate.duration = 10000

        // 子动画3:透明度动画
        val alpha = AlphaAnimation(1f, 0f)
        alpha.duration = 3000
        alpha.startOffset = 7000

        // 子动画4:缩放动画
        val scale1 = ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        scale1.duration = 1000
        scale1.startOffset = 4000

        // 步骤4:将创建的子动画添加到组合动画里
        setAnimation.addAnimation(alpha)
        setAnimation.addAnimation(rotate)
        setAnimation.addAnimation(translate)
        setAnimation.addAnimation(scale1)

        btn10.click {
            iv10.startAnimation(setAnimation)
        }

        btnStudyAnimListener.click {
            codeDialog.text("""
Animation类通过监听动画开始 / 结束 / 重复时刻来进行一系列操作，如跳转页面等等
通过在 Java 代码里setAnimationListener()方法设置

Animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // 动画开始时回调
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 动画结束时回调
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重复执行的时候回调
            }
        });

特别注意
若采取上述方法监听动画，每次监听都必须重写4个方法。

背景：有些时候我们并不需要监听动画的所有时刻
问题：但上述方式是必须需要重写4个时刻的方法，这显示太累赘
解决方案：采用动画适配器AnimatorListenerAdapter，解决
实现接口繁琐 的问题
具体如下：

anim.addListener(new AnimatorListenerAdapter() {
// 向addListener()方法中传入适配器对象AnimatorListenerAdapter()
// 由于AnimatorListenerAdapter中已经实现好每个接口
// 所以这里不实现全部方法也不会报错
    @Override
    public void onAnimationStart(Animator animation) {
    // 如想只想监听动画开始时刻，就只需要单独重写该方法就可以
    }
});
            """.trimIndent())
        }

    }

}
