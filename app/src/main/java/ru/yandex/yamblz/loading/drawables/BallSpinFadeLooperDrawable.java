package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class BallSpinFadeLooperDrawable extends DefaultLoadingDrawable {
    private float[] scaleFloats;
    private int[] alphas;
    private float[] positionsX, positionsY;

    public BallSpinFadeLooperDrawable() {
        super();
        countPositions();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float radius = 10;
        for (int i = 0; i < 8; i++) {
            canvas.save();
            canvas.translate(positionsX[i], positionsY[i]);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            defaultPaint.setAlpha(alphas[i]);
            canvas.drawCircle(0, 0, radius, defaultPaint);
            canvas.restore();
        }
    }

    private void countPositions() {
        positionsX = new float[9];
        positionsY = new float[9];
        float radius = 10;
        float radius2 = 50 - radius;
        for (int i = 0; i < 8; i++) {
            positionsX[i] = (float) (50 + radius2 * (Math.cos(i * (Math.PI / 4))));
            positionsY[i] = (float) (50 + radius2 * (Math.sin(i * (Math.PI / 4))));
        }
    }

    @Override
    protected List<ValueAnimator> createAnimators() {
        scaleFloats = new float[]{1f, 1f, 1f, 1f, 1f, 1f, 1f, 1f};
        alphas = new int[]{255, 255, 255, 255, 255, 255, 255, 255};
        List<ValueAnimator> animators = new ArrayList<>();
        int[] delays = {0, 120, 240, 360, 480, 600, 720, 780, 840};
        for (int i = 0; i < 8; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.4f, 1);
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(animation -> scaleFloats[index] = (float) animation.getAnimatedValue());

            ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 77, 255);
            alphaAnim.setDuration(1000);
            alphaAnim.setRepeatCount(-1);
            alphaAnim.setStartDelay(delays[i]);
            alphaAnim.addUpdateListener(animation -> alphas[index] = (int) animation.getAnimatedValue());
            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }

}
