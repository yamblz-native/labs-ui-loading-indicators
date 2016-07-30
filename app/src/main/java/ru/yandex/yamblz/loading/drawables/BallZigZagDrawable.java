package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class BallZigZagDrawable extends DefaultLoadingDrawable {
    float[] translateX, translateY;


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        for (int i = 0; i < 2; i++) {
            canvas.save();
            canvas.translate(translateX[i], translateY[i]);
            canvas.drawCircle(0, 0, 10, defaultPaint);
            canvas.restore();
        }
    }

    @Override
    List<Animator> createAnimators() {
        List<Animator> animators = new ArrayList<>();
        translateX = new float[2];
        translateY = new float[2];
        float startX = 100 / 6;
        float startY = 100 / 6;
        for (int i = 0; i < 2; i++) {
            final int index = i;
            ValueAnimator translateXAnim = ValueAnimator.ofFloat(startX, 100 - startX, 100 / 2, startX);
            if (i == 1) {
                translateXAnim = ValueAnimator.ofFloat(100 - startX, startX, 100 / 2, 100 - startX);
            }
            ValueAnimator translateYAnim = ValueAnimator.ofFloat(startY, startY, 100 / 2, startY);
            if (i == 1) {
                translateYAnim = ValueAnimator.ofFloat(100 - startY, 100 - startY, 100 / 2, 100 - startY);
            }

            translateXAnim.setDuration(1000);
            translateXAnim.setInterpolator(new LinearInterpolator());
            translateXAnim.setRepeatCount(-1);
            translateXAnim.addUpdateListener(animation -> {
                translateX[index] = (float) animation.getAnimatedValue();
            });

            translateYAnim.setDuration(1000);
            translateYAnim.setInterpolator(new LinearInterpolator());
            translateYAnim.setRepeatCount(-1);
            translateYAnim.addUpdateListener(animation -> {
                translateY[index] = (float) animation.getAnimatedValue();
            });
            animators.add(translateXAnim);
            animators.add(translateYAnim);
        }
        return animators;
    }
}
