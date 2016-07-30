package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

public class LineScalePulseOutDrawable extends LineScaleDrawable {
    @Override
    List<Animator> createAnimators() {
        scaleYFloats=new float[]{1f,1f,1f,1f,1f};
        List<Animator> animators=new ArrayList<>();
        long[] delays=new long[]{500,250,0,250,500};
        for (int i = 0; i < 5; i++) {
            final int index=i;
            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.3f,1);
            scaleAnim.setDuration(900);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(animation -> {
                scaleYFloats[index] = (float) animation.getAnimatedValue();
            });
            animators.add(scaleAnim);
        }
        return animators;
    }
}
