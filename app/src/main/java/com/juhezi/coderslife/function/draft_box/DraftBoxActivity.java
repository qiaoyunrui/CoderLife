package com.juhezi.coderslife.function.draft_box;

import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.SingleFragmentActivity;
import com.juhezi.coderslife.databinding.ActDraftBoxBinding;

/**
 * Created by qiao1 on 2017/2/17.
 */
public class DraftBoxActivity extends SingleFragmentActivity<ActDraftBoxBinding> {
    private static String TAG = "DraftBoxActivity";

    @Override
    protected Fragment getFragment() {
        return DraftBoxFragment.getInstance();
    }

    @Override
    protected int getActLayoutRes() {
        return R.layout.act_draft_box;
    }

    @Override
    protected int getFragContainerId() {
        return R.id.ll_frag_draft_box;
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
