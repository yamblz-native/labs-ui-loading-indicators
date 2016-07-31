package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class LineScalePartyDrawable extends DefaultLoadingDrawable {
    float[] scaleFloats = new float[]{1f, 1f, 1f, 1f, 1f};

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float translateX = 100 / 9;
        float translateY = 100 / 2;
        for (int i = 0; i < 4; i++) {
            canvas.save();
            canvas.translate((2 + i * 2) * translateX - translateX / 2, translateY);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            tempRectF.set(-translateX / 2, -100f / 2.5f, translateX / 2, 100f / 2.5f);
            canvas.drawRoundRect(tempRectF, 5, 5, defaultPaint);
            canvas.restore();
        }
    }

    @Override
    protected List<ValueAnimator> createAnimators() {
        List<ValueAnimator> animators = new ArrayList<>();
        long[] durations = new long[]{1260, 430, 1010, 730};
        long[] delays = new long[]{770, 290, 280, 740};
        for (int i = 0; i < 4; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.4f, 1);
            scaleAnim.setDuration(durations[i]);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(animation -> {
                scaleFloats[index] = (float) animation.getAnimatedValue();
            });
            animators.add(scaleAnim);
        }
        return animators;
    }
}
