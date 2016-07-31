package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class SquareSpinDrawable extends DefaultLoadingDrawableWithCamera {

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        tempRectF.set(20, 20, 80, 80);
        canvas.drawRect(tempRectF, defaultPaint);

    }

    @Override
    protected List<ValueAnimator> createAnimators() {
        List<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 180, 180, 0, 0);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2500);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(animation -> rotateX = (float) animation.getAnimatedValue());
        animators.add(valueAnimator);

        valueAnimator = ValueAnimator.ofFloat(0, 0, 180, 180, 0);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(2500);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(animation -> rotateY = (float) animation.getAnimatedValue());
        animators.add(valueAnimator);
        return animators;
    }
}
