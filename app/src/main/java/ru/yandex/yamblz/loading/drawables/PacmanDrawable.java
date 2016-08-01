package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class PacmanDrawable extends DefaultLoadingDrawable {
    private float translateX;
    private int alpha;
    private float degrees1;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawPacman(canvas, defaultPaint);
        drawCircle(canvas, defaultPaint);
    }

    private void drawPacman(Canvas canvas, Paint paint) {
        float x = 50;
        float y = 50;

        canvas.save();
        canvas.translate(x, y);
        paint.setAlpha(255);
        tempRectF.set(-x / 1.7f, -y / 1.7f, x / 1.7f, y / 1.7f);
        canvas.drawArc(tempRectF, degrees1, 360 - degrees1 * 2, true, paint);
        canvas.restore();
    }


    private void drawCircle(Canvas canvas, Paint paint) {
        float radius = 100f / 11;
        paint.setAlpha(alpha);
        canvas.drawCircle(translateX, 50, radius, paint);
    }

    @Override
    protected List<ValueAnimator> createAnimators() {
        List<ValueAnimator> animators = new ArrayList<>();
        float startT = 100 / 11;
        ValueAnimator translationAnim = ValueAnimator.ofFloat(100 - startT, 100 / 2);
        translationAnim.setDuration(650);
        translationAnim.setInterpolator(new LinearInterpolator());
        translationAnim.setRepeatCount(-1);
        translationAnim.addUpdateListener(animation -> {
            translateX = (float) animation.getAnimatedValue();
        });

        ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 122);
        alphaAnim.setDuration(650);
        alphaAnim.setRepeatCount(-1);
        alphaAnim.addUpdateListener(animation -> {
            alpha = (int) animation.getAnimatedValue();
        });

        ValueAnimator rotateAnim1 = ValueAnimator.ofFloat(0, 45, 0);
        rotateAnim1.setDuration(650);
        rotateAnim1.setRepeatCount(-1);
        rotateAnim1.addUpdateListener(animation -> {
            degrees1 = (float) animation.getAnimatedValue();
        });


        animators.add(translationAnim);
        animators.add(alphaAnim);
        animators.add(rotateAnim1);
        return animators;
    }
}
