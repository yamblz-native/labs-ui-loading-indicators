package ru.yandex.yamblz.loading.drawables;


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
    protected List<ValueAnimator> createAnimators() {
        List<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(0, 1);
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.addUpdateListener(animation -> scale = (float) animation.getAnimatedValue());
        ValueAnimator alphaAnim = ValueAnimator.ofInt(0, 255);
        alphaAnim.setInterpolator(new LinearInterpolator());
        alphaAnim.setDuration(1000);
        alphaAnim.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnim.addUpdateListener(animation -> alpha = (int) animation.getAnimatedValue());

        animators.add(scaleAnim);
        animators.add(alphaAnim);
        return animators;
    }
}
