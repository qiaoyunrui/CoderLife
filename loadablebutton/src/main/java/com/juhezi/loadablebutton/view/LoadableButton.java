package com.juhezi.loadablebutton.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.juhezi.loadablebutton.R;

/**
 * 带有进度条的Button
 * <p>
 * Created by qiao1 on 2017/2/20.
 */
public class LoadableButton extends FrameLayout {
    private static String TAG = "LoadableButton";

    private String text = "";
    private float textSize;

    private OnClickListener onClickListener;

    private boolean isLoading = false;

    private TextView textView;
    private ProgressBar progressBar;
    private View rootView;

    public LoadableButton(Context context) {
        super(context);
        init(context);
    }

    public LoadableButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.LoadableButton);
        text = typedArray.getString(R.styleable.LoadableButton_text);
        typedArray.getDimension(R.styleable.LoadableButton_textSize, 20);
        typedArray.recycle();
    }

    private void init(Context context) {
        rootView = LayoutInflater.from(context).inflate(R.layout.view_loadable_button, this, false);
        textView = (TextView) findViewById(R.id.tv_text);
        progressBar = (ProgressBar) findViewById(R.id.pb_load);
        textView.setText(text);
        textView.setTextSize(textSize);
        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
            }
        });
    }

    /**
     * 显示进度圈，隐藏TextView
     */
    public void load() {
        if (!isLoading) {
            textView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            isLoading = true;
        }
    }

    /**
     * 隐藏ProgresBar，显示TextView
     */
    public void unLoad() {
        if (isLoading) {
            textView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
            isLoading = false;
        }
    }

    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
