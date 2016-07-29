package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

public class OneArcDrawable extends DefaultLoadingDrawable{
    private final ValueAnimator rotateAnimator;
    private final ValueAnimator scaleAnimator;


    public OneArcDrawable() {
        super();
        defaultPaint.setStyle(Paint.Style.STROKE);
        rotateAnimator =ValueAnimator.ofFloat(0,360);
        rotateAnimator.setDuration(750);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.start();
        scaleAnimator=ValueAnimator.ofFloat(1,0.6f,0.5f,1);
        scaleAnimator.setDuration(750);
        scaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnimator.start();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float rotation=(Float) rotateAnimator.getAnimatedValue();
        float scale=(Float) scaleAnimator.getAnimatedValue();
        canvas.translate(50,50);
        canvas.scale(scale,scale);
        canvas.rotate(rotation);
        float circleSpace=10;
        tempRectF.set(-50+circleSpace,-50+circleSpace,50-circleSpace,50-circleSpace);
        canvas.drawArc(tempRectF,-45,270,false,defaultPaint);
    }

    @Override
    protected void update(long delta) {
        super.update(delta);
    }

}
