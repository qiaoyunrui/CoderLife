package com.juhezi.module.base.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/6/4.
 * Nothing Now
 */

public abstract class BaseFragment extends Fragment {

    protected View rootView;

    @LayoutRes
    protected abstract int getLayoutRes();

    protected void init() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutRes(), container, false);
        init();
        return rootView;
    }

    public AppCompatActivity getAppCompatActivity() {
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            return (AppCompatActivity) getActivity();
        }
        return null;
    }

}
