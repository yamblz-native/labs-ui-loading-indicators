package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class BallScaleDrawable extends DefaultLoadingDrawable {
    float scale = 1;
    int alpha = 255;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float circleSpacing = 4;
        defaultPaint.setAlpha(alpha);
        canvas.scale(scale, scale, 50, 50);
        defaultPaint.setAlpha(alpha);
        canvas.drawCircle(50, 50, 50 - circleSpacing, defaultPaint);
    }

    @Override
    protected List<ValueAnimator> createAnimators() {
        List<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator scaleAnim = ValueAnimator.ofFloat(0, 1);
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.addUpdateListener(animation -> scale = (float) animation.getAnimatedValue());

        ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 0);
        alphaAnim.setInterpolator(new LinearInterpolator());
        alphaAnim.setDuration(1000);
        alphaAnim.setRepeatCount(ValueAnimator.INFINITE);
        alphaAnim.addUpdateListener(animation -> alpha = (int) animation.getAnimatedValue());
        animators.add(scaleAnim);
        animators.add(alphaAnim);
        return animators;
    }
}
