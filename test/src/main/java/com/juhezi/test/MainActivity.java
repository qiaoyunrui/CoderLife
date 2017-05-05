package com.juhezi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.juhezi.module.base.router.Uri;
import com.juhezi.module.base.router.operator.manager.OMP;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    public static final Uri URL = new Uri(Uri.ACTIVITY, MainActivity.class.getName());

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
    }
}
