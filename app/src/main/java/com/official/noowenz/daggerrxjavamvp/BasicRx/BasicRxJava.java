package com.official.noowenz.daggerrxjavamvp.BasicRx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.official.noowenz.daggerrxjavamvp.R;
import com.official.noowenz.daggerrxjavamvp.registerModule.view.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicRxJava extends AppCompatActivity {

    @BindView(R.id.tv_first)
    Button tvFirst;
    @BindView(R.id.tv_second)
    Button tvSecond;
    @BindView(R.id.tv_third)
    Button tvThird;
    @BindView(R.id.tv_sign_up)
    Button tvSignUp;
    @BindView(R.id.tv_forth)
    Button tvForth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_rx_java);
        ButterKnife.bind(this);

    }

    public void onClickFirst(View v) {
        Intent i = new Intent(BasicRxJava.this, RxJavaSimpleActivity.class);
        startActivity(i);
    }

    public void onClickSecond(View v) {
        Intent i = new Intent(BasicRxJava.this, NamesActivity.class);
        startActivity(i);
    }

    public void onClickThird(View v) {
        Intent i = new Intent(BasicRxJava.this, BooksActivity.class);
        startActivity(i);
    }

    public void onClickForth(View v) {
        Intent i = new Intent(BasicRxJava.this, SchedulerActivity.class);
        startActivity(i);
    }

    public void onClickSignUp(View v) {
        Intent i = new Intent(BasicRxJava.this, RegisterActivity.class);
        startActivity(i);
    }
}
