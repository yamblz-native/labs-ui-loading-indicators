package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class TwoArcBallDrawable extends DefaultLoadingDrawable {

    private float scale1;
    private float scale2;
    private float rotation;

    public TwoArcBallDrawable() {
        super();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float circleSpacing = 10;

        canvas.save();
        canvas.translate(50, 50);
        canvas.scale(scale1, scale1);
        defaultPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 50 / 2.5f, defaultPaint);
        canvas.restore();

        canvas.translate(50, 50);
        canvas.scale(scale2, scale2);
        canvas.rotate(rotation);

        defaultPaint.setStyle(Paint.Style.STROKE);

        //draw two arc
        drawArc(canvas, 225, circleSpacing);
        drawArc(canvas, 45, circleSpacing);
    }

    @Override
    List<Animator> createAnimators() {
        List<Animator> animators = new ArrayList<>();
        ValueAnimator scaleAnim1 = ValueAnimator.ofFloat(1, 0.3f, 1);
        scaleAnim1.setDuration(1000);
        scaleAnim1.setRepeatCount(-1);
        scaleAnim1.addUpdateListener(anim->scale1= (float) anim.getAnimatedValue());
        animators.add(scaleAnim1);

        ValueAnimator scaleAnim2 = ValueAnimator.ofFloat(1, 0.3f, 1);
        scaleAnim2.setDuration(1000);
        scaleAnim2.setRepeatCount(-1);
        scaleAnim2.addUpdateListener(anim->scale2= (float) anim.getAnimatedValue());
        animators.add(scaleAnim2);

        ValueAnimator rotateAnim = ValueAnimator.ofFloat(0, 360);
        rotateAnim.setDuration(1000);
        rotateAnim.setRepeatCount(-1);
        rotateAnim.addUpdateListener(anim->rotation= (float) anim.getAnimatedValue());
        animators.add(rotateAnim);
        return animators;
    }

    private void drawArc(Canvas canvas, float startAngle, float circleSpacing) {
        tempRectF.set(-50 + circleSpacing, -50 + circleSpacing, 50 - circleSpacing, 50 - circleSpacing);
        canvas.drawArc(tempRectF, startAngle, 90, false, defaultPaint);
    }
}
