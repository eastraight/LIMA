package com.example.lima;

/*
 * Expand and collapse functionality based on:
 * https://stackoverflow.com/questions/46478952/expand-and-collapse-relativelayout-by-button-click
 */

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

/* B1_0 - ¿Qué es la violencia? */
public class B1_0Activity extends AppCompatActivity implements View.OnClickListener {
    Button b1_m01, b1_m02, b1_m03, b1_m04, b1_m05, b1_m06;
    RelativeLayout b1_01, b1_02, b1_03, b1_04, b1_05, b1_06;
    int h01, h02, h03, h04, h05, h06; // heights

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1_1);

        b1_01 = findViewById(R.id.b1_01);
        b1_02 = findViewById(R.id.b1_02);
        b1_03 = findViewById(R.id.b1_03);
        b1_04 = findViewById(R.id.b1_04);
        b1_05 = findViewById(R.id.b1_05);
        b1_06 = findViewById(R.id.b1_06);

        b1_m01 = findViewById(R.id.b1_Menu01);
        b1_m02 = findViewById(R.id.b1_Menu02);
        b1_m03 = findViewById(R.id.b1_Menu03);
        b1_m04 = findViewById(R.id.b1_Menu04);
        b1_m05 = findViewById(R.id.b1_Menu05);
        b1_m06 = findViewById(R.id.b1_Menu06);

        b1_m01.setOnClickListener(this);
        b1_m02.setOnClickListener(this);
        b1_m03.setOnClickListener(this);
        b1_m04.setOnClickListener(this);
        b1_m05.setOnClickListener(this);
        b1_m06.setOnClickListener(this);

        b1_01.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        b1_01.getViewTreeObserver().removeOnPreDrawListener(this);
                        // Start collapsed:
                        b1_01.setVisibility(View.GONE);
                        b1_02.setVisibility(View.GONE);
                        b1_03.setVisibility(View.GONE);
                        b1_04.setVisibility(View.GONE);
                        b1_05.setVisibility(View.GONE);
                        b1_06.setVisibility(View.GONE);

                        // Get heights:
                        h01 = b1_01.getMeasuredHeight();
                        h02 = b1_02.getMeasuredHeight();
                        h03 = b1_03.getMeasuredHeight();
                        h04 = b1_04.getMeasuredHeight();
                        h05 = b1_05.getMeasuredHeight();
                        h06 = b1_06.getMeasuredHeight();
                        return true;
                    }
                });
    }

    private void expand(RelativeLayout text, int height) {
        System.out.println(height);
        text.setVisibility(View.VISIBLE);
        ValueAnimator animator = slideAnimator(text, 0, height);
        animator.start();
    }

    private void collapse(final RelativeLayout text) {
        int finalHeight = text.getHeight();
        ValueAnimator mAnimator = slideAnimator(text, finalHeight, 0);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                text.setVisibility(View.GONE);
            }
            @Override
            public void onAnimationStart(Animator animator) {}
            @Override
            public void onAnimationCancel(Animator animator) {}
            @Override
            public void onAnimationRepeat(Animator animator) {}
        });
        mAnimator.start();
    }

    private ValueAnimator slideAnimator(final RelativeLayout text, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = text.getLayoutParams();
                layoutParams.height = value;
                text.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b1_Menu01:
                if (b1_01.getVisibility() == View.GONE) {
                    expand(b1_01, h01);
                } else collapse(b1_01);
                break;

            case R.id.b1_Menu02:
                if (b1_02.getVisibility() == View.GONE) {
                    expand(b1_02, h02);
                } else collapse(b1_02);
                break;

            case R.id.b1_Menu03:
                if (b1_03.getVisibility() == View.GONE) {
                    expand(b1_03, h03);
                } else collapse(b1_03);
                break;

            case R.id.b1_Menu04:
                if (b1_04.getVisibility() == View.GONE) {
                    expand(b1_04, h04);
                } else collapse(b1_04);
                break;

            case R.id.b1_Menu05:
                if (b1_05.getVisibility() == View.GONE) {
                    expand(b1_05, h05);
                } else collapse(b1_05);
                break;

            case R.id.b1_Menu06:
                if (b1_06.getVisibility() == View.GONE) {
                    expand(b1_06, h06);
                } else collapse(b1_06);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, B1Activity.class));
        overridePendingTransition(R.anim.animation_leave, R.anim.hold);
    }
}