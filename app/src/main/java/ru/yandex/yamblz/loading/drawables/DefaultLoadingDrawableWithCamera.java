package ru.yandex.yamblz.loading.drawables;


import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;

public abstract class DefaultLoadingDrawableWithCamera extends DefaultLoadingDrawable {
    private Camera camera;
    private Matrix matrix;
    float rotateX, rotateY, rotateZ;

    public DefaultLoadingDrawableWithCamera() {
        camera = new Camera();
        matrix = new Matrix();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        camera.save();
        camera.rotate(rotateX, rotateY, rotateZ);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-50, -50);
        matrix.postTranslate(50, 50);
        canvas.concat(matrix);
    }
}
