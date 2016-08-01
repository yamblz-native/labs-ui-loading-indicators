package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class BallScaleMultiplyDrawable extends DefaultLoadingDrawable {
    float[] scaleFloats;
    int[] alphaInts;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float circleSpacing = 4;
        for (int i = 0; i < 3; i++) {
            defaultPaint.setAlpha(alphaInts[i]);
            canvas.scale(scaleFloats[i], scaleFloats[i], 100 / 2, 100 / 2);
            canvas.drawCircle(100 / 2, 100 / 2, 100 / 2 - circleSpacing, defaultPaint);
        }
    }

    @Override
    protected List<ValueAnimator> createAnimators() {
        scaleFloats = new float[]{1, 1, 1};
        alphaInts = new int[]{255, 255, 255};
        List<ValueAnimator> animators = new ArrayList<>();
        long[] delays = new long[]{0, 200, 400};
        for (int i = 0; i < 3; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(0, 1);
            scaleAnim.setInterpolator(new LinearInterpolator());
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.addUpdateListener(animation -> scaleFloats[index] = (float) animation.getAnimatedValue());
            scaleAnim.setStartDelay(delays[i]);

            ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 0);
            alphaAnim.setInterpolator(new LinearInterpolator());
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
