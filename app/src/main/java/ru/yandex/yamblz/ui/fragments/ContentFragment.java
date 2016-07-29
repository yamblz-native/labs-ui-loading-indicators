package ru.yandex.yamblz.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.loading.drawables.OneArcDrawable;

public class ContentFragment extends BaseFragment {
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // ProgressDialog progressDialog=ProgressDialog.show(getContext(),null,null);
        ProgressDialog progressDialog1=new ProgressDialog(getContext());
        progressDialog1.setIndeterminateDrawable(new OneArcDrawable());
        progressDialog1.show();

    }
}
