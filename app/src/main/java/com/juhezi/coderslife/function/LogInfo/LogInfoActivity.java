package com.juhezi.coderslife.function.LogInfo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ActLogInfoBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.tools.Config;

/**
 * Created by qiao1 on 2017/1/22.
 */
public class LogInfoActivity extends AppCompatActivity {
    private static String TAG = "LogInfoActivity";

    private LogInfoViewModel viewModel;
    private ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActLogInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.act_log_info);
        viewModel = new LogInfoViewModel(binding, this, getInputLogContent());
        initActionBar(getInputLogContent());
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
        }
        return super.onOptionsItemSelected(item);
    }
}
