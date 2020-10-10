package com.example.lima;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class B1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1);
    }

    /* VF0 - ¿Qué es la violencia? */
    public void VF0Listener(View view) {
        Intent intent = new Intent(this, B1_0Activity.class);
        startActivity(intent);
        if (MainActivity.checkTransition()) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);
    }

    /* VF1 - Violencia en la pareja */
    public void VF1Listener(View view) {
        Intent intent = new Intent(this, B1_1Activity.class);
        startActivity(intent);
        if (MainActivity.checkTransition()) {
            this.overridePendingTransition(R.anim.animation_enter, R.anim.hold);
        } else this.overridePendingTransition(0, 0);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.animation_leave, R.anim.hold);
    }
}