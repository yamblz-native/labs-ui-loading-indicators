package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class BallPulseDrawable extends DefaultLoadingDrawable {
    private static final float SCALE = 1.0f;
    //scale x ,y
    private float[] scaleFloats = new float[]{SCALE,
            SCALE,
            SCALE};

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        float circleSpacing=3;
        float radius=15;
        float x = radius;
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float translateX=x+(radius*2)*i+circleSpacing*i;
            canvas.translate(translateX, 50);
            canvas.scale(scaleFloats[i], scaleFloats[i]);
            canvas.drawCircle(0, 0, radius, defaultPaint);
            canvas.restore();
        }
    }


    @Override
    List<Animator> createAnimators() {
        List<Animator> animators = new ArrayList<>();
        int[] delays = new int[]{120, 240, 360};
        for (int i = 0; i < 3; i++) {
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.3f, 1);
            scaleAnim.setDuration(750);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);

            int index = i;
            scaleAnim.addUpdateListener(animation -> {
                scaleFloats[index] = (float) animation.getAnimatedValue();
            });
            animators.add(scaleAnim);
        }
        return animators;
    }
}
