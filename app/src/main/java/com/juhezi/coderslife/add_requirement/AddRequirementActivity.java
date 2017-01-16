package com.juhezi.coderslife.add_requirement;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ActAddRequirementBinding;
import com.juhezi.coderslife.entry.Title;

/**
 * Created by qiao1 on 2017/1/16.
 */
public class AddRequirementActivity extends AppCompatActivity {

    private static final String TAG = "AddRequirementActivity";
    private ActAddRequirementBinding mBinding;
    private AddRequViewModel viewModel;
    private Toolbar mToolbar;
    private ActionBar mActionBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_add_requirement);
        viewModel = new AddRequViewModel(mBinding);
        initActionBar();
    }

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.tb_act_add_fragrequirement);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
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
