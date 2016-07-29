package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class BallPulseRaiseDrawable extends DefaultLoadingDrawable {
    private Camera camera;
    private Matrix matrix;
    float rotateX;

    public BallPulseRaiseDrawable() {
        super();
        camera = new Camera();
        matrix = new Matrix();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        camera.save();
        camera.rotate(rotateX,0,0);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-50, -50);
        matrix.postTranslate(50, 50);
        canvas.concat(matrix);

        float radius=10;
        canvas.drawCircle(30,radius*2,radius,defaultPaint);
        canvas.drawCircle(70,radius*2,radius,defaultPaint);

        canvas.drawCircle(radius,100-2*radius,radius,defaultPaint);
        canvas.drawCircle(50,100-2*radius,radius,defaultPaint);
        canvas.drawCircle(100-radius,100-2*radius,radius,defaultPaint);
    }

    @Override
    public List<Animator> createAnimators() {
        List<Animator> animators = new ArrayList<>();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 360);
        valueAnimator.addUpdateListener(animation -> {
            rotateX = (float) animation.getAnimatedValue();
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(1500);
        animators.add(valueAnimator);
        return animators;
    }
}
