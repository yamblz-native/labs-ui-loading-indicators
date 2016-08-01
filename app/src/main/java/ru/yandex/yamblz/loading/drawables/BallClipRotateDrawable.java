package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class BallClipRotateDrawable extends DefaultLoadingDrawable {
    private float rotation;
    private float scale;

    public BallClipRotateDrawable() {
        super();
        defaultPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.translate(50, 50);
        canvas.scale(scale, scale);
        canvas.rotate(rotation);
        float circleSpace = 10;
        tempRectF.set(-50 + circleSpace, -50 + circleSpace, 50 - circleSpace, 50 - circleSpace);
        canvas.drawArc(tempRectF, -45, 270, false, defaultPaint);
    }


    @Override
    protected List<ValueAnimator> createAnimators() {
        List<ValueAnimator> animators = new ArrayList<>();
        ValueAnimator rotateAnimator = ValueAnimator.ofFloat(0, 360);
        rotateAnimator.setDuration(750);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.addUpdateListener(animation -> rotation = (float) animation.getAnimatedValue());
        animators.add(rotateAnimator);

        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1, 0.6f, 0.5f, 1);
        scaleAnimator.setDuration(750);
        scaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnimator.addUpdateListener(animation -> scale = (float) animation.getAnimatedValue());
        animators.add(scaleAnimator);
        return animators;
    }

}
