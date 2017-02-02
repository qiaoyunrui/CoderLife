package com.juhezi.coderslife.function.all_logs;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.SingleFragmentActivity;
import com.juhezi.coderslife.databinding.ActAllLogsBinding;

/**
 * Created by qiao1 on 2017/1/25.
 */
public class AllLogsActivity extends SingleFragmentActivity<ActAllLogsBinding> {
    private static String TAG = "AllLogsActivity";

    @Override
    protected Fragment getFragment() {
        return AllLogsFragment.getInstance();
    }

    @Override
    protected int getActLayoutRes() {
        return R.layout.act_all_logs;
    }

    @Override
    protected int getFragContainerId() {
        return R.id.ll_frag_act_all_logs;
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
