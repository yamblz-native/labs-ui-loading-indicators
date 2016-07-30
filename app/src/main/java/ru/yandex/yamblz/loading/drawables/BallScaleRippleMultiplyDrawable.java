package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class BallScaleRippleMultiplyDrawable extends BallScaleMultiplyDrawable {
    public BallScaleRippleMultiplyDrawable() {
        super();
        defaultPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    List<Animator> createAnimators() {
        scaleFloats = new float[]{1, 1, 1};
        alphaInts = new int[]{255, 255, 255};
        List<Animator> animators = new ArrayList<>();
        long[] delays = new long[]{0, 200, 400};
        for (int i = 0; i < 3; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(0, 1);
            scaleAnim.setInterpolator(new LinearInterpolator());
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.addUpdateListener(animation -> scaleFloats[index] = (float) animation.getAnimatedValue());
            scaleAnim.setStartDelay(delays[i]);

            ValueAnimator alphaAnim = ValueAnimator.ofInt(0, 255);
            scaleAnim.setInterpolator(new LinearInterpolator());
            alphaAnim.setDuration(1000);
            alphaAnim.setRepeatCount(ValueAnimator.INFINITE);
            alphaAnim.addUpdateListener(animation -> alphaInts[index] = (int) animation.getAnimatedValue());
            scaleAnim.setStartDelay(delays[i]);

            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }
}
