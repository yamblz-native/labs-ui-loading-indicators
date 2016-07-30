package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class TriangleSkewSpinDrawable extends DefaultLoadingDrawable {
    private final Camera camera;
    private final Matrix matrix;
    private final Path path;
    private float rotateX, rotateY;

    public TriangleSkewSpinDrawable() {
        this.path = new Path();
        camera = new Camera();
        matrix = new Matrix();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        camera.save();
        camera.rotate(rotateX, rotateY, 0);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-50, -50);
        matrix.postTranslate(50, 50);
        canvas.concat(matrix);
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
        rotationX.setRepeatCount(-1);
        rotationX.setDuration(2500);
        rotationX.addUpdateListener(animation -> {
            rotateX = (float) animation.getAnimatedValue();
        });
        ValueAnimator rotationY = ValueAnimator.ofFloat(0, 0, 180, 180, 0);
        rotationY.setInterpolator(new LinearInterpolator());
        rotationY.setRepeatCount(-1);
        rotationY.setDuration(2500);
        rotationY.addUpdateListener(animation -> {
            rotateY = (float) animation.getAnimatedValue();
        });
        animators.add(rotationX);
        animators.add(rotationY);
        return animators;
    }
}
