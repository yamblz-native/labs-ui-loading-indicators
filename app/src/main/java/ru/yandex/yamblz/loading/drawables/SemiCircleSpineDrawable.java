package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class SemiCircleSpineDrawable extends DefaultLoadingDrawable {
    float rotation;

    @Override
    protected List<ValueAnimator> createAnimators() {
        List<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator rotateAnim = ValueAnimator.ofFloat(0, 180, 360);
        rotateAnim.setDuration(600);
        rotateAnim.setRepeatCount(-1);
        rotateAnim.start();
        rotateAnim.addUpdateListener(animation -> {
            rotation = (float) animation.getAnimatedValue();
        });
        animators.add(rotateAnim);
        return animators;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        tempRectF.set(0, 0, 100, 100);
        canvas.rotate(rotation, 50, 50);
        canvas.drawArc(tempRectF, -60, 120, false, defaultPaint);
    }
}
