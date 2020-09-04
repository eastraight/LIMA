package com.example.lima;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/* VF1 - ¿Qué es la violencia? */
public class VF0Activity extends AppCompatActivity {
    Button vfm01, vfm02, vfm03, vfm04, vfm05, vfm06;
    TextView vf01, vf02, vf03, vf04, vf05, vf06;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_f_0);

        vfm01 = new Button(this);
        vf01=new TextView(this);
        //vf01.setOnClickListener(this);

        vfm01 = (Button)findViewById(R.id.VFMenu01);
        vf01=(TextView)findViewById(R.id.VF01);
        //vf01.setVisibility(TextView.INVISIBLE);


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, VFActivity.class));
        overridePendingTransition(R.anim.animation_leave, R.anim.hold);
    }
}
