package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class BallBeatDrawable extends DefaultLoadingDrawable {
    private float[] scaleFloats;
    private int[] alphas;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float circleSpacing = 4f;
        float radius = (100f - circleSpacing * 2f) / 6f;
        float x = 100f / 2 - (radius * 2 + circleSpacing);
        float y = 50f;
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float translateX = x + (radius * 2) * i + circleSpacing * i;
            canvas.translate(translateX, y);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            defaultPaint.setAlpha(alphas[i]);
            canvas.drawCircle(0, 0, radius, defaultPaint);
            canvas.restore();
        }
    }

    @Override
    protected List<ValueAnimator> createAnimators() {
        scaleFloats = new float[]{1f, 1f, 1f};
        alphas = new int[]{255, 255, 255};
        List<ValueAnimator> animators = new ArrayList<>();
        int[] delays = new int[]{350, 0, 350};
        for (int i = 0; i < 3; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.75f, 1);
            scaleAnim.setDuration(700);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(animation -> {
                scaleFloats[index] = (float) animation.getAnimatedValue();

            });

            ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 51, 255);
            alphaAnim.setDuration(700);
            alphaAnim.setRepeatCount(ValueAnimator.INFINITE);
            alphaAnim.setStartDelay(delays[i]);
            alphaAnim.addUpdateListener(animation -> alphas[index] = (int) animation.getAnimatedValue());

            scaleAnim.start();
            alphaAnim.start();
            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }
}
