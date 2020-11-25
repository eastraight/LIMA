package com.example.lima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class B1EvalActivityStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1_eval_start);
    }

    /* Evaluation */
    public void StartListener(View view) {
        Intent intent = new Intent(this, B1EvalActivity.class);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);
    }


}