package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class BallZigZagDeflectDrawable extends BallZigZagDrawable {
    @Override
    List<Animator> createAnimators() {
        translateX = new float[2];
        translateY = new float[2];
        List<Animator> animators=new ArrayList<>();
        float startX=100f/6;
        float startY=100f/6;
        for (int i = 0; i < 2; i++) {
            final int index=i;
            ValueAnimator translateXAnim=ValueAnimator.ofFloat(startX,100-startX,startX,100-startX,startX);
            if (i==1){
                translateXAnim=ValueAnimator.ofFloat(100f-startX,startX,100f-startX,startX,100f-startX);
            }
            ValueAnimator translateYAnim=ValueAnimator.ofFloat(startY,startY,100f-startY,100f-startY,startY);
            if (i==1){
                translateYAnim=ValueAnimator.ofFloat(100f-startY,100f-startY,startY,startY,100f-startY);
            }

            translateXAnim.setDuration(2000);
            translateXAnim.setInterpolator(new LinearInterpolator());
            translateXAnim.setRepeatCount(-1);
            translateXAnim.addUpdateListener(animation -> translateX [index]= (float) animation.getAnimatedValue());

            translateYAnim.setDuration(2000);
            translateYAnim.setInterpolator(new LinearInterpolator());
            translateYAnim.setRepeatCount(-1);
            translateYAnim.addUpdateListener(animation -> translateY [index]= (float) animation.getAnimatedValue());
            animators.add(translateXAnim);
            animators.add(translateYAnim);
        }
        return animators;
    }

}
