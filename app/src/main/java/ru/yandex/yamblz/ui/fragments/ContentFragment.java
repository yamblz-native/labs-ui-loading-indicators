package ru.yandex.yamblz.ui.fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.loading.drawables.BallBeatDrawable;
import ru.yandex.yamblz.loading.drawables.BallClipRotateDrawable;
import ru.yandex.yamblz.loading.drawables.BallClipRotateMultipleDrawable;
import ru.yandex.yamblz.loading.drawables.BallClipRotatePulseDrawable;
import ru.yandex.yamblz.loading.drawables.BallGridBeatDrawable;
import ru.yandex.yamblz.loading.drawables.BallGridPulseDrawable;
import ru.yandex.yamblz.loading.drawables.BallPulseDrawable;
import ru.yandex.yamblz.loading.drawables.BallPulseRaiseDrawable;
import ru.yandex.yamblz.loading.drawables.BallPulseSyncDrawable;
import ru.yandex.yamblz.loading.drawables.BallRotateDrawable;
import ru.yandex.yamblz.loading.drawables.BallScaleDrawable;
import ru.yandex.yamblz.loading.drawables.BallScaleMultiplyDrawable;
import ru.yandex.yamblz.loading.drawables.BallScaleRippleDrawable;
import ru.yandex.yamblz.loading.drawables.BallScaleRippleMultiplyDrawable;
import ru.yandex.yamblz.loading.drawables.BallSpinFadeLooperDrawable;
import ru.yandex.yamblz.loading.drawables.BallTrianglePathDrawable;
import ru.yandex.yamblz.loading.drawables.BallZigZagDeflectDrawable;
import ru.yandex.yamblz.loading.drawables.BallZigZagDrawable;
import ru.yandex.yamblz.loading.drawables.CubeTransitionDrawable;
import ru.yandex.yamblz.loading.drawables.DefaultLoadingDrawable;
import ru.yandex.yamblz.loading.drawables.LineScaleDrawable;
import ru.yandex.yamblz.loading.drawables.LineScalePartyDrawable;
import ru.yandex.yamblz.loading.drawables.LineScalePulseOutDrawable;
import ru.yandex.yamblz.loading.drawables.LineScalePulseOutRapidDrawable;
import ru.yandex.yamblz.loading.drawables.LineSpinFadeDrawable;
import ru.yandex.yamblz.loading.drawables.PacmanDrawable;
import ru.yandex.yamblz.loading.drawables.SemiCircleSpineDrawable;
import ru.yandex.yamblz.loading.drawables.SquareSpinDrawable;
import ru.yandex.yamblz.loading.drawables.TriangleSkewSpinDrawable;

public class ContentFragment extends BaseFragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ProgressDialog progressDialog1 = new ProgressDialog(getContext());
        progressDialog1.setIndeterminateDrawable(new TriangleSkewSpinDrawable());
        //progressDialog1.show();
        LinearLayout row1 = (LinearLayout) view.findViewById(R.id.row1);
        row1.addView(getImageView(new BallPulseDrawable()));
        row1.addView(getImageView(new BallGridPulseDrawable()));
        row1.addView(getImageView(new BallClipRotateDrawable()));
        row1.addView(getImageView(new BallClipRotatePulseDrawable()));

        LinearLayout row2 = (LinearLayout) view.findViewById(R.id.row2);
        row2.addView(getImageView(new SquareSpinDrawable()));
        row2.addView(getImageView(new BallClipRotateMultipleDrawable()));
        row2.addView(getImageView(new BallPulseRaiseDrawable()));
        row2.addView(getImageView(new BallRotateDrawable()));

        LinearLayout row3 = (LinearLayout) view.findViewById(R.id.row3);
        row3.addView(getImageView(new CubeTransitionDrawable()));
        row3.addView(getImageView(new BallZigZagDrawable()));
        row3.addView(getImageView(new BallZigZagDeflectDrawable()));
        row3.addView(getImageView(new BallTrianglePathDrawable()));

        LinearLayout row4 = (LinearLayout) view.findViewById(R.id.row4);
        row4.addView(getImageView(new BallScaleDrawable()));
        row4.addView(getImageView(new LineScaleDrawable()));
        row4.addView(getImageView(new LineScalePartyDrawable()));
        row4.addView(getImageView(new BallScaleMultiplyDrawable()));

        LinearLayout row5 = (LinearLayout) view.findViewById(R.id.row5);
        row5.addView(getImageView(new BallPulseSyncDrawable()));
        row5.addView(getImageView(new BallBeatDrawable()));
        row5.addView(getImageView(new LineScalePulseOutDrawable()));
        row5.addView(getImageView(new LineScalePulseOutRapidDrawable()));

        LinearLayout row6= (LinearLayout) view.findViewById(R.id.row6);
        row6.addView(getImageView(new BallScaleRippleDrawable()));
        row6.addView(getImageView(new BallScaleRippleMultiplyDrawable()));
        row6.addView(getImageView(new BallSpinFadeLooperDrawable()));
        row6.addView(getImageView(new LineSpinFadeDrawable()));

        LinearLayout row7= (LinearLayout) view.findViewById(R.id.row7);
        row7.addView(getImageView(new TriangleSkewSpinDrawable()));
        row7.addView(getImageView(new PacmanDrawable()));
        row7.addView(getImageView(new BallGridBeatDrawable()));
        row7.addView(getImageView(new SemiCircleSpineDrawable()));
        //((ViewGroup) view).addView(getImageView(new TriangleSkewSpinDrawable()));
    }


    private ImageView getImageView(DefaultLoadingDrawable drawable) {
        drawable.setColor(Color.WHITE);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) getContext().getResources().getDimension(R.dimen.image_width),
                (int) getContext().getResources().getDimension(R.dimen.image_height));
        int margin= (int) getContext().getResources().getDimension(R.dimen.image_margin);
        layoutParams.setMargins(0,margin,margin,0);
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(drawable);
        return imageView;
    }
}
