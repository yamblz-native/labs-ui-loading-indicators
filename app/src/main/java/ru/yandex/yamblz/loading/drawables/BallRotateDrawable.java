package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class BallRotateDrawable extends DefaultLoadingDrawable {
    float scale=0.5f;
    float rotation;
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float radius=10;

        canvas.save();
        canvas.rotate(rotation,50,50);
        canvas.translate(50 - radius * 3, 50);
        canvas.scale(scale, scale);
        canvas.drawCircle(0, 0, radius, defaultPaint);
        canvas.restore();

        canvas.save();
        canvas.translate(50, 50);
        canvas.scale(scale, scale);
        canvas.drawCircle(0, 0, radius, defaultPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(rotation,50,50);
        canvas.translate(50 + radius * 3, 50);
        canvas.scale(scale, scale);
        canvas.drawCircle(0,0,radius, defaultPaint);
        canvas.restore();
    }

    @Override
    protected List<ValueAnimator> createAnimators() {
        List<ValueAnimator> animators=new ArrayList<>();
        ValueAnimator scaleAnim=ValueAnimator.ofFloat(0.5f,1,0.5f);
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.addUpdateListener(animation -> scale = (float) animation.getAnimatedValue());

        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0,180,360);
        rotateAnim.addUpdateListener(animation -> rotation= (float) animation.getAnimatedValue());
        rotateAnim.setDuration(1000);
        rotateAnim.setRepeatCount(ValueAnimator.INFINITE);

        animators.add(scaleAnim);
        animators.add(rotateAnim);
        return animators;
    }
}
