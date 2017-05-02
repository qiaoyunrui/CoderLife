package com.juhezi.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.juhezi.module.base.router.URI;
import com.juhezi.module.base.router.operator.manager.OperatorManagerProxy;

public class MainActivity extends AppCompatActivity {

    private Button mButton;

    public static final URI URL = new URI(URI.ACTIVITY, MainActivity.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_turn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OperatorManagerProxy.invoke(MainActivity.this, URL);
                startActivity(intent);
            }
        });
    }
}
