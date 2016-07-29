package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class BallClipRotateMultipleDrawable extends DefaultLoadingDrawable {
    float scale,rotation;

    public BallClipRotateMultipleDrawable() {
        super();
        defaultPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    List<Animator> createAnimators() {
        List<Animator> animators=new ArrayList<>();
        ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.6f,1);
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(-1);
        scaleAnim.addUpdateListener(animation -> {
            scale = (float) animation.getAnimatedValue();
        });
        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0, 180,360);
        rotateAnim.setDuration(1000);
        rotateAnim.setRepeatCount(-1);
        rotateAnim.addUpdateListener(animation -> {
            rotation = (float) animation.getAnimatedValue();
        });
        animators.add(scaleAnim);
        animators.add(rotateAnim);
        return animators;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float circleSpacing=12;

        canvas.save();
        canvas.translate(50, 50);
        canvas.scale(scale, scale);
        canvas.rotate(rotation);

        //draw two big arc
        drawArc(canvas,circleSpacing,135,90);
        drawArc(canvas,circleSpacing,-45,90);

        canvas.restore();
        canvas.translate(50, 50);
        canvas.scale(scale, scale);
        canvas.rotate(-rotation);
        circleSpacing=circleSpacing*2.2f;
        //draw two small arc
        drawArc(canvas,circleSpacing,225,90);
        drawArc(canvas,circleSpacing,45,90);
    }

    private void drawArc(Canvas canvas,float circleSpacing,float startAngle,float endAngle){
        tempRectF.set(-50+circleSpacing,-50+circleSpacing,50-circleSpacing,50-circleSpacing);
        canvas.drawArc(tempRectF, startAngle, endAngle, false, defaultPaint);
    }
}
