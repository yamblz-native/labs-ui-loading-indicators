package ru.yandex.yamblz.ui.fragments;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.loading.drawables.BallScaleMiltipleDrawable;

public class ContentFragment extends BaseFragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*TableLayout tableLayout=new TableLayout(getContext());
        TableLayout.LayoutParams layoutParams=new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tableLayout.setLayoutParams(layoutParams);
        TableRow tableRow = new TableRow(getContext());
        tableRow.addView(getImageView(new BallPulseDrawable()));
        tableRow.addView(getImageView(new BallGridPulseDrawable()));
        tableRow.addView(getImageView(new BallGridPulseDrawable()));
        tableRow.addView(getImageView(new BallClipRotateDrawable()));
        tableRow.addView(getImageView(new BallClipRotatePulseDrawable()));
        tableLayout.addView(tableRow);
        tableRow = new TableRow(getContext());
        tableRow.addView(getImageView(new SquareSpinDrawable()));
        tableRow.addView(getImageView(new BallClipRotateMultipleDrawable()));
        tableRow.addView(getImageView(new BallPulseRaiseDrawable()));
        tableLayout.addView(tableRow);
        ((ViewGroup) view).addView(tableLayout);*/

       // ProgressDialog progressDialog=ProgressDialog.show(getContext(),null,null);
        ProgressDialog progressDialog1=new ProgressDialog(getContext());
        progressDialog1.setIndeterminateDrawable(new BallScaleMiltipleDrawable());
        progressDialog1.show();

    }
    private ImageView getImageView(Drawable drawable){
        ImageView imageView=new ImageView(getContext());
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(drawable);
        return imageView;
    }
}
