package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class BallScaleRippleDrawable extends BallScaleDrawable {
    public BallScaleRippleDrawable() {
        defaultPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    List<Animator> createAnimators() {
        List<Animator> animators=new ArrayList<>();
        ValueAnimator scaleAnim=ValueAnimator.ofFloat(0,1);
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        scaleAnim.addUpdateListener(animation -> scale = (float) animation.getAnimatedValue());
        ValueAnimator alphaAnim=ValueAnimator.ofInt(0, 255);
        alphaAnim.setInterpolator(new LinearInterpolator());
        alphaAnim.setDuration(1000);
        alphaAnim.setRepeatCount(-1);
        alphaAnim.addUpdateListener(animation -> {
            alpha = (int) animation.getAnimatedValue();
        });

        animators.add(scaleAnim);
        animators.add(alphaAnim);
        return animators;
    }
}
