package com.example.lima;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/* VF1 - ¿Qué es la violencia? */
public class VF0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_f_0);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, VFActivity.class));
        overridePendingTransition(R.anim.animation_leave, R.anim.hold);
    }
}
