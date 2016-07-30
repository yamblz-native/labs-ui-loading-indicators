package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class BallTrianglePathDrawable extends DefaultLoadingDrawable {
    float[] translateX,translateY;

    public BallTrianglePathDrawable() {
        super();
        defaultPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < 3; i++) {
            canvas.save();
            canvas.translate(translateX[i], translateY[i]);
            canvas.drawCircle(0, 0, 10, defaultPaint);
            canvas.restore();
        }
    }
    @Override
    List<Animator> createAnimators() {
        List<Animator> animators=new ArrayList<>();
        float startX=20;
        float startY=20;
        translateX=new float[3];
        translateY=new float[3];
        for (int i = 0; i < 3; i++) {
            final int index=i;
            ValueAnimator translateXAnim=ValueAnimator.ofFloat(100/2,100-startX,startX,100/2);
            if (i==1){
                translateXAnim=ValueAnimator.ofFloat(100-startX,startX,100/2,100-startX);
            }else if (i==2){
                translateXAnim=ValueAnimator.ofFloat(startX,100/2,100-startX,startX);
            }
            ValueAnimator translateYAnim=ValueAnimator.ofFloat(startY,100-startY,100-startY,startY);
            if (i==1){
                translateYAnim=ValueAnimator.ofFloat(100-startY,100-startY,startY,100-startY);
            }else if (i==2){
                translateYAnim= ValueAnimator.ofFloat(100-startY,startY,100-startY,100-startY);
            }

            translateXAnim.setDuration(2000);
            translateXAnim.setInterpolator(new LinearInterpolator());
            translateXAnim.setRepeatCount(-1);
            translateXAnim.addUpdateListener(animation -> {
                translateX [index]= (float) animation.getAnimatedValue();
            });
            translateXAnim.start();

            translateYAnim.setDuration(2000);
            translateYAnim.setInterpolator(new LinearInterpolator());
            translateYAnim.setRepeatCount(-1);
            translateYAnim.addUpdateListener(animation -> {
                translateY [index]= (float) animation.getAnimatedValue();
            });
            translateYAnim.start();

            animators.add(translateXAnim);
            animators.add(translateYAnim);
        }
        return animators;
    }
}
