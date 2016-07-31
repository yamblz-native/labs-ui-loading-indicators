package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class BallGridPulseDrawable extends DefaultLoadingDrawable {
    //тк у анимаций есть задержка этот код работает
    private int[] alphas = new int[]{255, 255, 255, 255, 255, 255, 255, 255, 255};
    private float[] scaleFloats = new float[]{1, 1, 1, 1, 1, 1, 1, 1, 1};

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float circleSpacing = 3;
        float radius = 15;
        float y = 15;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                canvas.save();
                float translateX = radius + (radius * 2) * j + circleSpacing * j;
                float translateY = y + (radius * 2) * i + circleSpacing * i;
                canvas.translate(translateX, translateY);
                canvas.scale(scaleFloats[3 * i + j], scaleFloats[3 * i + j]);
                defaultPaint.setAlpha(alphas[3 * i + j]);
                canvas.drawCircle(0, 0, radius, defaultPaint);
                canvas.restore();
            }
        }
    }

    @Override
    protected List<ValueAnimator> createAnimators() {
        List<ValueAnimator> animators = new ArrayList<>();
        int[] durations = {720, 1020, 1280, 1420, 1450, 1180, 870, 1450, 1060};
        int[] delays = {-60, 250, -170, 480, 310, 30, 460, 780, 450};

        for (int i = 0; i < 9; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.5f, 1);
            scaleAnim.setDuration(durations[i]);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(animation -> scaleFloats[index] = (float) animation.getAnimatedValue());

            ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 210, 122, 255);
            alphaAnim.setDuration(durations[i]);
            alphaAnim.setRepeatCount(ValueAnimator.INFINITE);
            alphaAnim.setStartDelay(delays[i]);
            alphaAnim.addUpdateListener(animation -> alphas[index] = (int) animation.getAnimatedValue());

            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }
}
