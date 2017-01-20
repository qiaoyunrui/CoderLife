package com.juhezi.coderslife.function.main;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.SingleFragmentActivity;
import com.juhezi.coderslife.databinding.ActMainBinding;
import com.juhezi.coderslife.entry.Title;

/**
 * Created by qiao1 on 2017/1/11.
 */
public class MainActivity extends SingleFragmentActivity<ActMainBinding> {
    private static String TAG = "MainActivity";

    private Toolbar mToolbar;
    private ActionBar mActionBar;

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;

    private Title title = new Title(Title.TODAY_TITLE);

    @Override
    protected Fragment getFragment() {
        return MainFragment.getInstance();
    }

    @Override
    protected int getActLayoutRes() {
        return R.layout.act_main;
    }

    @Override
    protected int getFragContainerId() {
        return R.id.rl_act_main_frag;
    }

    @Override
    protected void init(ActMainBinding binding) {
        super.init(binding);
        initView();
        initActionBar();
        initData(binding);
    }

    private void initView() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_act_main);
        mNavigationView = (NavigationView) findViewById(R.id.nv_act_main);
    }

    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.tb_act_main);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setTitle(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.drawer_layout_open_desc,
                R.string.drawer_layout_close_desc);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initData(ActMainBinding binding) {
        binding.setTitle(title);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (((MainFragment) fragment).isItemOpen()) { //如果Item呈打开状态
            ((MainFragment) fragment).closeItem();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult: ");
    }
}
