package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class BallSpinFadeLooperDrawable extends DefaultLoadingDrawable {
    float[] scaleFloats;
    int[] alphas;

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float radius=10;
        for (int i = 0; i < 8; i++) {
            canvas.save();
            Point point=circleAt(100,100,100/2-radius,i*(Math.PI/4));
            canvas.translate(point.x,point.y);
            canvas.scale(scaleFloats[i],scaleFloats[i]);
            defaultPaint.setAlpha(alphas[i]);
            canvas.drawCircle(0,0,radius,defaultPaint);
            canvas.restore();
        }
    }
    Point circleAt(int width,int height,float radius,double angle){
        float x= (float) (width/2+radius*(Math.cos(angle)));
        float y= (float) (height/2+radius*(Math.sin(angle)));
        return new Point(x,y);
    }

    @Override
    List<Animator> createAnimators() {
        scaleFloats=new float[]{1f,1f,1f,1f,1f,1f,1f,1f};
        alphas=new int[]{255,255,255,255,255,255,255,255};
        List<Animator> animators=new ArrayList<>();
        int[] delays= {0, 120, 240, 360, 480, 600, 720, 780, 840};
        for (int i = 0; i < 8; i++) {
            final int index=i;
            ValueAnimator scaleAnim=ValueAnimator.ofFloat(1,0.4f,1);
            scaleAnim.setDuration(1000);
            scaleAnim.setRepeatCount(-1);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(animation -> scaleFloats[index] = (float) animation.getAnimatedValue());

            ValueAnimator alphaAnim=ValueAnimator.ofInt(255, 77, 255);
            alphaAnim.setDuration(1000);
            alphaAnim.setRepeatCount(-1);
            alphaAnim.setStartDelay(delays[i]);
            alphaAnim.addUpdateListener(animation -> {
                alphas[index] = (int) animation.getAnimatedValue();
            });
            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }

    final class Point{
        public float x;
        public float y;

        public Point(float x, float y){
            this.x=x;
            this.y=y;
        }
    }
}
