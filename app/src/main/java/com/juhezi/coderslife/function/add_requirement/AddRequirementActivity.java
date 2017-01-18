package com.juhezi.coderslife.function.add_requirement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ActAddRequirementBinding;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Created by qiao1 on 2017/1/16.
 */
public class AddRequirementActivity extends AppCompatActivity {

    private static final String TAG = "AddRequirementActivity";
    private ActAddRequirementBinding mBinding;
    private AddRequViewModel viewModel;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private TextInputLayout mTilWrapper;
    private Button mBtnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_add_requirement);
        viewModel = new AddRequViewModel(mBinding, this);
        initActionBar();
        initView();
    }

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.tb_act_add_fragrequirement);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
    }

    private void initView() {
        mBtnAdd = (Button) findViewById(R.id.btn_act_add_requirement_ok);
        mTilWrapper = (TextInputLayout) findViewById(R.id.til_content_wrapper);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkContentValidity()) {
                    //插入数据，回到上一个界面
                    viewModel.submitLogContent(null);
                }
            }
        });
    }

    /**
     * 检测内容合法性
     *
     * @return
     */
    private boolean checkContentValidity() {
        if (mTilWrapper.getEditText().getText().toString().trim().length() == 0) {    //输入内容为空
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
