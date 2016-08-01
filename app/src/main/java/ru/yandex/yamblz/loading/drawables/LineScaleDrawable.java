package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class LineScaleDrawable extends DefaultLoadingDrawable {
    float[] scaleYFloats;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float translateX = 100f / 11;
        float translateY = 50f;
        for (int i = 0; i < 5; i++) {
            canvas.save();
            canvas.translate((2 + i * 2) * translateX - translateX / 2f, translateY);
            canvas.scale(1, scaleYFloats[i]);
            tempRectF.set(-translateX / 2, -100 / 2.5f, translateX / 2, 100 / 2.5f);
            canvas.drawRoundRect(tempRectF, 5, 5, defaultPaint);
            canvas.restore();
        }
    }

    @Override
    protected List<ValueAnimator> createAnimators() {
        scaleYFloats = new float[]{1f, 1f, 1f, 1f, 1f};
        List<ValueAnimator> animators = new ArrayList<>();
        long[] delays = new long[]{100, 200, 300, 400, 500};
        for (int i = 0; i < 5; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.4f, 1);
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(animation -> {
                scaleYFloats[index] = (float) animation.getAnimatedValue();
            });
            animators.add(scaleAnim);
        }
        return animators;
    }
}
