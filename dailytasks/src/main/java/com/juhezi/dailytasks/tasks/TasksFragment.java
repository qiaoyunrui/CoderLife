package com.juhezi.dailytasks.tasks;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.juhezi.dailytasks.R;
import com.juhezi.module.base.common.BaseFragment;
import com.juhezi.module.base.util.KeyBoardUtil;
import com.juhezi.module.base.util.TypefaceBuilder;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/24.
 */

public class TasksFragment extends BaseFragment implements TasksContract.View {

    private static final String TAG = "TasksFragment";

    private TasksContract.Presenter mPresenter;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private FloatingActionButton mFabAdd;
    private CardView mCvAddWrapper;
    private EditText mEtAddContent;
    private ImageView mImgAdd;

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static TasksFragment newInsatnce() {
        return new TasksFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tasks;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TasksPresenter(this);
    }

    @Override
    protected void init() {
        mToolbar = (Toolbar) rootView.findViewById(R.id.tasks_fragment_tb);
        getAppCompatActivity().setSupportActionBar(mToolbar);
        mActionBar = getAppCompatActivity().getSupportActionBar();
        mActionBar.setTitle(null);
        TextView textView = (TextView) rootView.findViewById(R.id.tasks_fragment_tv_title);
        textView.setTypeface(TypefaceBuilder.build());
        mFabAdd = (FloatingActionButton) rootView.findViewById(R.id.tasks_fragment_fab_add_task);
        mCvAddWrapper = (CardView) rootView.findViewById(R.id.tasks_fragment_cv_add_wrapper);
        mEtAddContent = (EditText) rootView.findViewById(R.id.tasks_fragment_et_add_content);
        mImgAdd = (ImageView) rootView.findViewById(R.id.tasks_fragment_img_add);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWrapper(true);
            }
        });
        mImgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
        mEtAddContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    send();
                }
                return false;
            }
        });
    }

    void send() {
        String content = mEtAddContent.getText().toString();
        mEtAddContent.setText("");
        //show Fab & hide Wrapper
        Log.i(TAG, "onClick: " + content);
        showWrapper(false);
    }

    void showWrapper(boolean isShow) {
        if (isShow) {
            mFabAdd.hide();
            mCvAddWrapper.setVisibility(View.VISIBLE);
            KeyBoardUtil.openKeyBoard(getContext(), mEtAddContent);
        } else {
            mFabAdd.show();
            mCvAddWrapper.setVisibility(View.INVISIBLE);
            KeyBoardUtil.closeKeyBoard(getContext(), mEtAddContent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
        }
        super.onDestroy();
    }
}
