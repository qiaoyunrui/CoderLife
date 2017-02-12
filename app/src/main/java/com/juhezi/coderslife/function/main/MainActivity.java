package com.juhezi.coderslife.function.main;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.SingleFragmentActivity;
import com.juhezi.coderslife.databinding.ActMainBinding;
import com.juhezi.coderslife.entry.Title;
import com.juhezi.coderslife.function.all_logs.AllLogsActivity;

import java.util.UUID;

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
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.item_all_logs:
                        turn2AllLogsAct();
                        break;
                }
                return false;
            }
        });
        Log.i(TAG, "initActionBar: " + mActionBar);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_delete_all:
                if (((MainFragment) fragment).canBeDeleted()) {
                    comfirmDeleteDialog();
                } else {
                    showToast(getString(R.string.do_not_delete));
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void comfirmDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.confirm_delete_all_log))
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainFragment) fragment).deleteTodayAllLogs();
                    }
                }).setNegativeButton(getString(R.string.cancel), null)
                .create()
                .show();
    }

    private void turn2AllLogsAct() {
        Intent intent = new Intent(this, AllLogsActivity.class);
        startActivity(intent);
    }

    /**
     * 隐藏Toolbar
     */
    public void hideActionBar() {
        mActionBar.hide();
    }

    public void showActionBar() {
        mActionBar.show();
    }

}
