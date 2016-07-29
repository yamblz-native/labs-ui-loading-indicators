package ru.yandex.yamblz.loading.drawables;


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

public class DefaultLoadingDrawable extends Drawable implements Runnable{
    protected Paint defaultPaint =new Paint();
    protected RectF tempRectF;
    protected Handler handler;
    protected long lastFrame;

    public DefaultLoadingDrawable(){
        defaultPaint=new Paint();
        defaultPaint.setColor(Color.BLACK);
        tempRectF=new RectF();
        handler=new Handler(Looper.getMainLooper());
        lastFrame= SystemClock.uptimeMillis();
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.save();
        //рисуем в оазмерах 1 к 1
        //начало координат левый нижний угол
        canvas.translate(0,canvas.getHeight());
        canvas.scale(canvas.getWidth()/100,-canvas.getHeight()/100);
        nextFrame();
    }

    protected void nextFrame(){
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

    protected void update(long delta){
        //работает только если установлен коллбек, вьюхи его ставят сами
        //каждый кадр просим перерисовать себя=)
        invalidateSelf();
    }
}
