package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class CubeTransitionDrawable extends DefaultLoadingDrawable {
    float[] translateX,translateY;
    float degrees,scaleFloat=1.0f;

    @Override
    List<Animator> createAnimators() {
        translateX=new float[2];
        translateY=new float[2];
        List<Animator> animators=new ArrayList<>();
        float startX=20;
        float startY=20;
        for (int i = 0; i < 2; i++) {
            final int index=i;
            translateX[index]=startX;
            ValueAnimator translationXAnim=ValueAnimator.ofFloat(startX,100-startX,100-startX, startX,startX);
            if (i==1){
                translationXAnim=ValueAnimator.ofFloat(100-startX,startX,startX, 100-startX,100-startX);
            }
            translationXAnim.setInterpolator(new LinearInterpolator());
            translationXAnim.setDuration(1600);
            translationXAnim.setRepeatCount(-1);
            translationXAnim.addUpdateListener(animation -> translateX[index] = (float) animation.getAnimatedValue());

            translateY[index]=startY;
            ValueAnimator translationYAnim=ValueAnimator.ofFloat(startY,startY,100-startY,100- startY,startY);
            if (i==1){
                translationYAnim=ValueAnimator.ofFloat(100-startY,100-startY,startY,startY,100-startY);
            }
            translationYAnim.setDuration(1600);
            translationYAnim.setInterpolator(new LinearInterpolator());
            translationYAnim.setRepeatCount(-1);
            translationYAnim.addUpdateListener(animation -> {
                translateY[index] = (float) animation.getAnimatedValue();
            });

            animators.add(translationXAnim);
            animators.add(translationYAnim);
        }

        ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.5f,1,0.5f,1);
        scaleAnim.setDuration(1600);
        scaleAnim.setInterpolator(new LinearInterpolator());
        scaleAnim.setRepeatCount(-1);
        scaleAnim.addUpdateListener(animation -> scaleFloat = (float) animation.getAnimatedValue());

        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0,180,360,1.5f*360,2*360);
        rotateAnim.setDuration(1600);
        rotateAnim.setInterpolator(new LinearInterpolator());
        rotateAnim.setRepeatCount(-1);
        rotateAnim.addUpdateListener(animation -> degrees = (float) animation.getAnimatedValue());
        rotateAnim.start();

        animators.add(scaleAnim);
        animators.add(rotateAnim);
        return animators;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float rWidth=20;
        float rHeight=20;
        for (int i = 0; i < 2; i++) {
            canvas.save();
            canvas.translate(translateX[i], translateY[i]);
            canvas.rotate(degrees);
            canvas.scale(scaleFloat,scaleFloat);
            tempRectF.set(-rWidth/2,-rHeight/2,rWidth/2,rHeight/2);
            canvas.drawRect(tempRectF,defaultPaint);
            canvas.restore();
        }
    }
}
