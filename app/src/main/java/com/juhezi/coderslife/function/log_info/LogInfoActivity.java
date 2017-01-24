package com.juhezi.coderslife.function.log_info;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ActLogInfoBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.coderslife.tools.Config;
import com.konifar.fab_transformation.FabTransformation;

/**
 * Created by qiao1 on 2017/1/22.
 */
public class LogInfoActivity extends AppCompatActivity {
    private static String TAG = "LogInfoActivity";

    private LogInfoViewModel viewModel;
    private ActionBar actionBar;
    private Button mBtnSave;
    private ProgressBar mProgressBar;
    private EditText mEtContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActLogInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.act_log_info);
        viewModel = new LogInfoViewModel(binding, this, getInputLogContent());
        initActionBar(getInputLogContent());
        initEvent();
    }

    private void initEvent() {
        mBtnSave = (Button) findViewById(R.id.btn_act_log_info_save);
        mProgressBar = (ProgressBar) findViewById(R.id.pb_act_log_info);
        mEtContent = (EditText) findViewById(R.id.et_act_log_info_content);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkContentValidity()) {
                    showToast(getString(R.string.content_can_not_be_empty));
                    return;
                }
                showProgressBar();
                //保存修改
                viewModel.save(new Action1<Integer>() {
                    @Override
                    public void onAction(final Integer integer) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (integer == Config.RESULT_CODE_OK) {
                                    showToast(getString(R.string.save_change_success));
                                    Intent intent = new Intent();
                                    intent.putExtra(Config.LOG_SAVED, viewModel.getLogContent());
                                    setResult(Config.TAG_LOG_INFO_RETURN_SAVE, intent);
                                    finish();
                                } else {
                                    hideProgressBar();
                                    showToast(getString(R.string.save_change_fail));
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void initActionBar(LogContent logContent) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_act_log_info);
        switch (logContent.getContentType()) {
            case LogContent.TYPE_REQUIREMENT:
                toolbar.setBackgroundColor(getResources().getColor(R.color.requirement_color));
                break;
            case LogContent.TYPE_BUG:
                toolbar.setBackgroundColor(getResources().getColor(R.color.bug_color));
                break;
            case LogContent.TYPE_VERSION:
                toolbar.setBackgroundColor(getResources().getColor(R.color.version_color));
                break;
            default:
                toolbar.setBackgroundColor(getResources().getColor(R.color.other_color));
        }
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

    }

    /**
     * 获取传入的LogContent
     *
     * @return
     */
    private LogContent getInputLogContent() {
        Intent intent = getIntent();
        LogContent logContent = (LogContent) intent.getSerializableExtra(Config.SHOW_LOG_CONTENT_INFO);
        return logContent;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_log_info_delete:
                showConfirmDeleteDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 加载Menu文件
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_log_info_actionbar, menu);
        return true;
    }

    private void showProgressBar() {
        if (mProgressBar.getVisibility() != View.VISIBLE) {
            FabTransformation.with(mBtnSave)
                    .transformTo(mProgressBar);
        }
    }

    private void hideProgressBar() {
        if (mProgressBar.getVisibility() == View.VISIBLE) {
            FabTransformation.with(mBtnSave)
                    .transformFrom(mProgressBar);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 检查内容是否合法
     */
    private boolean checkContentValidity() {
        return mEtContent.getText().toString().trim().length() != 0;
    }

    /**
     * 显示确认删除对话框
     */
    private void showConfirmDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.confirm_delete_this_log))
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showProgressBar();
                        viewModel.delete(new Action1<Integer>() {
                            @Override
                            public void onAction(final Integer integer) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (integer == Config.RESULT_CODE_OK) {
                                            setResult(Config.TAG_LOG_INFO_RETURN_DELETE);
                                            finish();
                                        } else {
                                            hideProgressBar();
                                        }
                                    }
                                });
                            }
                        });
                        //删除本条日志
                    }
                }).setNegativeButton(R.string.cancel, null)
                .create().show();
    }

}


