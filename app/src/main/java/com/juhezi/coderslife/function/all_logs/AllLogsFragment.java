package com.juhezi.coderslife.function.all_logs;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.FragAllLogsBinding;

/**
 * Created by qiao1 on 2017/1/25.
 */
public class AllLogsFragment extends Fragment {
    private static String TAG = "AllLogsFragment";

    private AllLogsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_all_logs, container, false);
        FragAllLogsBinding binding = DataBindingUtil.bind(view);
        viewModel = new AllLogsViewModel(binding, getContext());
        return view;
    }

    private static AllLogsFragment sInstance;

    public static AllLogsFragment getInstance() {
        if (sInstance == null) {
            sInstance = new AllLogsFragment();
        }
        return sInstance;
    }

}
