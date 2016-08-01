package ru.yandex.yamblz.loading.drawables;


import android.animation.ValueAnimator;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

public class BallPulseSyncDrawable extends DefaultLoadingDrawable {
    float[] translateYFloats;

    @Override
    protected List<ValueAnimator> createAnimators() {
        translateYFloats = new float[3];
        List<ValueAnimator> animators=new ArrayList<>();
        float circleSpacing=4;
        float radius=(100-circleSpacing*2)/6;
        int[] delays=new int[]{70,140,210};
        for (int i = 0; i < 3; i++) {
            final int index=i;
            ValueAnimator scaleAnim=ValueAnimator.ofFloat(100/2,100/2-radius*2,100/2);
            scaleAnim.setDuration(600);
            scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
            scaleAnim.setStartDelay(delays[i]);
            scaleAnim.addUpdateListener(animation -> translateYFloats[index] = (float) animation.getAnimatedValue());
            animators.add(scaleAnim);
        }
        return animators;
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float circleSpacing=4;
        float radius=(100-circleSpacing*2)/6f;
        float x = 100f/ 2-(radius*2+circleSpacing);
        for (int i = 0; i < 3; i++) {
            canvas.save();
            float translateX=x+(radius*2)*i+circleSpacing*i;
            canvas.translate(translateX, translateYFloats[i]);
            canvas.drawCircle(0, 0, radius, defaultPaint);
            canvas.restore();
        }
    }
}
