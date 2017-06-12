package com.juhezi.test;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.juhezi.module.base.router.JUri;
import com.juhezi.module.base.router.operator.manager.OMP;
import com.juhezi.module.base.util.IntentUtil;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    public static final JUri URL = new JUri(JUri.ACTIVITY, MainActivity.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_turn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OMP.invoke(MainActivity.this, URL);
                startActivity(intent);
            }
        });
        Button button2 = (Button) findViewById(R.id.btn_turn_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                startActivity(IntentUtil.getOpenDocumentIntent(IntentUtil.IMAGE_TYPE));
            }
        });

    }
}
