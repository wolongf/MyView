package com.example.myview;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.renderscript.AllocationAdapter;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 抛物线
     * @param view
     */
    public void parabola(View view)
    {
      //  ImageView mBlueBall = (ImageView) this.findViewById(R.id.id_bamuwei);
//
//        ValueAnimator valueAnimator = new ValueAnimator();
//        valueAnimator.setDuration(3000);
//        valueAnimator.setObjectValues(new PointF(0, 0));
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.setEvaluator(new TypeEvaluator<PointF>()
//        {
//            @Override
//            public PointF evaluate(float fraction, PointF startValue,
//                                   PointF endValue)
//            {
//                PointF point = new PointF();
//                point.x = 200 * fraction * 3;
//                point.y = 200 * (fraction * 3) * (fraction * 3);
//                return point;
//            }
//        });
//
//        valueAnimator.start();
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
//        {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation)
//            {
//                PointF point = (PointF) animation.getAnimatedValue();
//                mBlueBall.setX(point.x);
//                mBlueBall.setY(point.y);
//
//            }
//        });

 //       startAnimation(mBlueBall);
    }

    /**
     * 要start 动画的那张图片的ImageView
     * @param View
     */
   public void startAnimation(View View) {
       //分900步进行移动动画
        final int count = 900;
        ImageView imageView  = (ImageView) this.findViewById(R.id.id_bamuwei);

        Keyframe[] keyframes = new Keyframe[count];
        final float keyStep = 1f / (float) count;
        float key = keyStep;
        for (int i = 0; i < count; ++i) {
            keyframes[i] = Keyframe.ofFloat(key, i + 1);
            key += keyStep;
        }
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe("translationX", keyframes); //设置x坐标
        key = keyStep;
        for (int i = 0; i < count; ++i) {
            keyframes[i] = Keyframe.ofFloat(key, -getY(i + 1));
            key += keyStep;
        }
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofKeyframe("translationY", keyframes); //设置y坐标
        ObjectAnimator yxBouncer = ObjectAnimator.ofPropertyValuesHolder(imageView, pvhY, pvhX).setDuration(4500);
        yxBouncer.setInterpolator(new BounceInterpolator());
        yxBouncer.start();
    }

    /**
     * 这里为抛物线方程
     *
     *
     * @param x
     * @return
     */
    private float getY(float x) {
        final float a = -1f / 200f;
        return a * x * x + 4 * x;
    }

}