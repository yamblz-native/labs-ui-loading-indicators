package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class BallGridBeatDrawable extends DefaultLoadingDrawable {
    private int[] alphas;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float circleSpacing = 4;
        float radius = (100 - circleSpacing * 4) / 6f;
        float x = 100f / 2f - (radius * 2 + circleSpacing);
        float y = 100f / 2f - (radius * 2 + circleSpacing);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.save();
                float translateX = x + (radius * 2) * j + circleSpacing * j;
                float translateY = y + (radius * 2) * i + circleSpacing * i;
                canvas.translate(translateX, translateY);
                defaultPaint.setAlpha(alphas[3 * i + j]);
                canvas.drawCircle(0, 0, radius, defaultPaint);
                canvas.restore();
            }
        }
    }

    @Override
    List<Animator> createAnimators() {
        alphas = new int[]{255, 255, 255, 255, 255, 255, 255, 255, 255, 255};
        List<Animator> animators = new ArrayList<>();

        int[] durations = {960, 930, 1190, 1130, 1340, 940, 1200, 820, 1190};
        int[] delays = {360, 400, 680, 410, 710, -150, -120, 10, 320};

        for (int i = 0; i < 9; i++) {
            final int index = i;
            ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 168, 255);
            alphaAnim.setDuration(durations[i]);
            alphaAnim.setRepeatCount(ValueAnimator.INFINITE);
            alphaAnim.setStartDelay(delays[i]);
            alphaAnim.addUpdateListener(animation -> {
                alphas[index] = (int) animation.getAnimatedValue();
            });
            animators.add(alphaAnim);
        }
        return animators;
    }
}
