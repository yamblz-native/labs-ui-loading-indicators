package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class MyQwesomeRandomDrawable extends DefaultLoadingDrawable {
    float scale,rotation;
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
        float[] bStartAngles=new float[]{135,-45};
        for (int i = 0; i < 2; i++) {
            tempRectF.set(-50+circleSpacing,-50+circleSpacing,50-circleSpacing,50-circleSpacing);
            canvas.drawArc(tempRectF, bStartAngles[i], 90, false, defaultPaint);
        }

        canvas.restore();
        canvas.translate(50, 50);
        canvas.scale(scale, scale);
        canvas.rotate(-rotation);
        //draw two small arc
        float[] sStartAngles=new float[]{225,45};
        for (int i = 0; i < 2; i++) {
            tempRectF.set(-50+circleSpacing,-50+circleSpacing,50-circleSpacing,50-circleSpacing);
            canvas.drawArc(tempRectF, sStartAngles[i], 90, false, defaultPaint);
        }
    }
}
