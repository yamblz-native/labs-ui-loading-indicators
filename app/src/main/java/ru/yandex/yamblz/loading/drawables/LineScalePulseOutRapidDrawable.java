package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

public class LineScalePulseOutRapidDrawable extends LineScaleDrawable {
    @Override
    List<Animator> createAnimators() {
        scaleYFloats = new float[]{1f, 1f, 1f, 1f, 1f};
        List<Animator> animators = new ArrayList<>();
        long[] delays = new long[]{400, 200, 0, 200, 400};
        for (int i = 0; i < 5; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.4f, 1);
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(animation -> {
                scaleYFloats[index] = (float) animation.getAnimatedValue();
            });
            scaleAnim.start();
            animators.add(scaleAnim);
        }
        return animators;
    }
}
