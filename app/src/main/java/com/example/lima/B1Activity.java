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

/* B1 - La violencia: introducción */
public class B1Activity extends AppCompatActivity implements View.OnClickListener {
    Button b1_1, b1_2, b1_3, b1_4, b1_5, b1_6;
    RelativeLayout layout1, layout2, layout3, layout4, layout5, layout6;
    int h1, h2, h3, h4, h5, h6; // layout heights

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1);

        layout1 = findViewById(R.id.b1_1layout);
        layout2 = findViewById(R.id.b1_2layout);
        layout3 = findViewById(R.id.b1_3layout);
        layout4 = findViewById(R.id.b1_4layout);
        layout5 = findViewById(R.id.b1_5layout);
        layout6 = findViewById(R.id.b1_6layout);

        b1_1 = findViewById(R.id.b1_1);
        b1_2 = findViewById(R.id.b1_2);
        b1_3 = findViewById(R.id.b1_3);
        b1_4 = findViewById(R.id.b1_4);
        b1_5 = findViewById(R.id.b1_5);
        b1_6 = findViewById(R.id.b1_6);

        b1_1.setOnClickListener(this);
        b1_2.setOnClickListener(this);
        b1_3.setOnClickListener(this);
        b1_4.setOnClickListener(this);
        b1_5.setOnClickListener(this);
        b1_6.setOnClickListener(this);

        layout1.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        layout1.getViewTreeObserver().removeOnPreDrawListener(this);
                        // Start collapsed:
                        layout1.setVisibility(View.GONE);
                        layout2.setVisibility(View.GONE);
                        layout3.setVisibility(View.GONE);
                        layout4.setVisibility(View.GONE);
                        layout5.setVisibility(View.GONE);
                        layout6.setVisibility(View.GONE);

                        // Get heights:
                        h1 = layout1.getMeasuredHeight();
                        h2 = layout2.getMeasuredHeight();
                        h3 = layout3.getMeasuredHeight();
                        h4 = layout4.getMeasuredHeight();
                        h5 = layout5.getMeasuredHeight();
                        h6 = layout6.getMeasuredHeight();
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
            case R.id.b1_1:
                if (layout1.getVisibility() == View.GONE) {
                    expand(layout1, h1);
                } else collapse(layout1);
                break;

            case R.id.b1_2:
                if (layout2.getVisibility() == View.GONE) {
                    expand(layout2, h2);
                } else collapse(layout2);
                break;

            case R.id.b1_3:
                if (layout3.getVisibility() == View.GONE) {
                    expand(layout3, h3);
                } else collapse(layout3);
                break;

            case R.id.b1_4:
                if (layout4.getVisibility() == View.GONE) {
                    expand(layout4, h4);
                } else collapse(layout4);
                break;

            case R.id.b1_5:
                if (layout5.getVisibility() == View.GONE) {
                    expand(layout5, h5);
                } else collapse(layout5);
                break;

            case R.id.b1_6:
                if (layout6.getVisibility() == View.GONE) {
                    expand(layout6, h6);
                } else collapse(layout6);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.animation_leave, R.anim.hold);
    }
}