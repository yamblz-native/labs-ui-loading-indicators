package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class OneArcDrawable extends DefaultLoadingDrawable{
    private final ValueAnimator valueAnimator;

    public OneArcDrawable() {
        super();
        defaultPaint.setAntiAlias(true);
        defaultPaint.setStrokeWidth(10f);
        defaultPaint.setStyle(Paint.Style.STROKE);
        valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        tempRectF.set(0,0,100,100);
       // canvas.drawOval(tempRectF,defaultPaint);
        float startAngle=310*(Float)valueAnimator.getAnimatedValue();
        Log.d("Dsad",""+startAngle);
        canvas.drawArc(tempRectF,startAngle,310,false,defaultPaint);
        canvas.restore();
    }

    @Override
    protected void update(long delta) {
        super.update(delta);
    }

}
