package com.juhezi.coderslife.function.add_log;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ActAddLogBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.entry.LogTypeEntry;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.coderslife.tools.Config;
import com.konifar.fab_transformation.FabTransformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao1 on 2017/1/16.
 */
public class AddLogActivity extends AppCompatActivity {

    private static final String TAG = "AddLogActivity";
    private ActAddLogBinding mBinding;
    private AddLogViewModel viewModel;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private TextInputLayout mTilWrapper;
    private Button mBtnAdd;
    private ProgressBar mProgressBar;
    private Spinner mSpinner;
    private LogTypeSpinnerAdapter mAdapter;
    private List<LogTypeEntry> mLogTypeEntries = new ArrayList<>();
    ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_add_log);
        viewModel = new AddLogViewModel(mBinding, this);
        initActionBar();
        initView();
        initLogType();
    }

    /**
     * 初始化日志类型
     */
    private void initLogType() {
        mLogTypeEntries.add(new LogTypeEntry(getString(R.string.requirement), R.drawable.ic_requirement, LogContent.TYPE_REQUIREMENT));
        mLogTypeEntries.add(new LogTypeEntry(getString(R.string.bug), R.drawable.ic_bug, LogContent.TYPE_BUG));
        mLogTypeEntries.add(new LogTypeEntry(getString(R.string.version), R.drawable.ic_version, LogContent.TYPE_VERSION));
        mAdapter.setList(mLogTypeEntries);
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
        mProgressBar = (ProgressBar) findViewById(R.id.pb_act_add_requirement);
        mSpinner = (Spinner) findViewById(R.id.spinner_act_add_log);
        mAdapter = new LogTypeSpinnerAdapter();
        mSpinner.setAdapter(mAdapter);
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkContentValidity()) {
                    FabTransformation.with(mBtnAdd)
                            .transformTo(mProgressBar);

                    //插入数据，回到上一个界面
                    viewModel.submitLogContent(((LogTypeEntry) mSpinner.getSelectedItem()).getLogType(),
                            new Action1<Integer>() {
                                @Override
                                public void onAction(final Integer integer) {
                                    AddLogActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if (integer == Config.RESULT_CODE_OK) {
                                                showToast("添加日志成功");
                                                Intent intent = new Intent();
                                                intent.putExtra(Config.ADD_REQUIREMENT_LOG_CONTENT, mBinding.getLogContent());
                                                setResult(Config.TAG_ADD_REQUIREMENT_RETURN, intent);
                                                finish();
                                            } else {
                                                showToast("添加日志失败");
                                            }
                                        }
                                    });
                                }
                            });
                } else {
                    showToast("日志无法添加");
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

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
