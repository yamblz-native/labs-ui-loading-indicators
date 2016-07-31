package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Path;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class TriangleSkewSpinDrawable extends DefaultLoadingDrawableWithCamera {
    private final Path path;

    public TriangleSkewSpinDrawable() {
        super();
        this.path = new Path();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        path.moveTo(20, 80);
        path.lineTo(80, 80);
        path.lineTo(50, 20);
        path.close();
        canvas.drawPath(path, defaultPaint);
    }

    @Override
    List<Animator> createAnimators() {
        List<Animator> animators = new ArrayList<>();
        ValueAnimator rotationX = ValueAnimator.ofFloat(0, 180, 180, 0, 0);
        rotationX.setInterpolator(new LinearInterpolator());
        rotationX.setRepeatCount(ValueAnimator.INFINITE);
        rotationX.setDuration(2500);
        rotationX.addUpdateListener(animation -> {
            rotateX = (float) animation.getAnimatedValue();
        });
        ValueAnimator rotationY = ValueAnimator.ofFloat(0, 0, 180, 180, 0);
        rotationY.setInterpolator(new LinearInterpolator());
        rotationY.setRepeatCount(ValueAnimator.INFINITE);
        rotationY.setDuration(2500);
        rotationY.addUpdateListener(animation -> {
            rotateY = (float) animation.getAnimatedValue();
        });
        animators.add(rotationX);
        animators.add(rotationY);
        return animators;
    }
}
