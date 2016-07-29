package ru.yandex.yamblz.loading.drawables;


import android.animation.Animator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import java.util.List;

public abstract class DefaultLoadingDrawable extends Drawable implements Runnable{
    public static final float SCALE=1;
    public static final int ALPHA=255;

    Paint defaultPaint =new Paint();
    RectF tempRectF;
    private Handler handler;
    private long lastFrame;
    private List<Animator> animators;
    DefaultLoadingDrawable(){
        defaultPaint=new Paint();
        defaultPaint.setColor(Color.BLACK);
        defaultPaint.setStrokeWidth(6);
        defaultPaint.setAntiAlias(true);
        tempRectF=new RectF();
        handler=new Handler(Looper.getMainLooper());
        lastFrame= SystemClock.uptimeMillis();
        animators=createAnimators();
        startAnimators();
    }
    @Override
    public void draw(Canvas canvas) {
        //рисуем в оазмерах 100 к 100
        canvas.scale(canvas.getWidth()/100f,canvas.getHeight()/100f);
        nextFrame();
    }

    private void nextFrame(){
        handler.post(this);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void run() {
        long currentFrame=SystemClock.uptimeMillis();
        long delta=currentFrame-lastFrame;
        lastFrame=currentFrame;
        update(delta);
       // Log.d("DefaultLoadingDrawable","delta:"+delta);
    }

    private void startAnimators(){
        for(Animator a:animators){
            a.start();
        }
    }

    protected void update(long delta){
        //работает только если установлен коллбек, вьюхи его ставят сами
        //каждый кадр просим перерисовать себя=)
        invalidateSelf();
    }

    abstract List<Animator> createAnimators();

    @Override
    public int getIntrinsicWidth() {
        return 1;
    }

    @Override
    public int getIntrinsicHeight() {
        return 1;
    }
}
