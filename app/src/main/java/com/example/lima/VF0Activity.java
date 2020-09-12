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

/* VF0 - ¿Qué es la violencia? */
public class VF0Activity extends AppCompatActivity implements View.OnClickListener {
    Button vfm01, vfm02, vfm03, vfm04, vfm05, vfm06;
    RelativeLayout vf01, vf02, vf03, vf04, vf05, vf06;
    int h01, h02, h03, h04, h05, h06; // heights

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_f_0);

        vf01 = findViewById(R.id.VF01);
        vf02 = findViewById(R.id.VF02);
        vf03 = findViewById(R.id.VF03);
        vf04 = findViewById(R.id.VF04);
        vf05 = findViewById(R.id.VF05);
        vf06 = findViewById(R.id.VF06);

        vfm01 = findViewById(R.id.VFMenu01);
        vfm02 = findViewById(R.id.VFMenu02);
        vfm03 = findViewById(R.id.VFMenu03);
        vfm04 = findViewById(R.id.VFMenu04);
        vfm05 = findViewById(R.id.VFMenu05);
        vfm06 = findViewById(R.id.VFMenu06);

        vfm01.setOnClickListener(this);
        vfm02.setOnClickListener(this);
        vfm03.setOnClickListener(this);
        vfm04.setOnClickListener(this);
        vfm05.setOnClickListener(this);
        vfm06.setOnClickListener(this);

        vf01.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        vf01.getViewTreeObserver().removeOnPreDrawListener(this);
                        // Start collapsed:
                        vf01.setVisibility(View.GONE);
                        vf02.setVisibility(View.GONE);
                        vf03.setVisibility(View.GONE);
                        vf04.setVisibility(View.GONE);
                        vf05.setVisibility(View.GONE);
                        vf06.setVisibility(View.GONE);

                        // Get heights:
                        h01 = vf01.getMeasuredHeight();
                        h02 = vf02.getMeasuredHeight();
                        h03 = vf03.getMeasuredHeight();
                        h04 = vf04.getMeasuredHeight();
                        h05 = vf05.getMeasuredHeight();
                        h06 = vf06.getMeasuredHeight();
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
            case R.id.VFMenu01:
                if (vf01.getVisibility() == View.GONE) {
                    expand(vf01, h01);
                } else collapse(vf01);
                break;

            case R.id.VFMenu02:
                if (vf02.getVisibility() == View.GONE) {
                    expand(vf02, h02);
                } else collapse(vf02);
                break;

            case R.id.VFMenu03:
                if (vf03.getVisibility() == View.GONE) {
                    expand(vf03, h03);
                } else collapse(vf03);
                break;

            case R.id.VFMenu04:
                if (vf04.getVisibility() == View.GONE) {
                    expand(vf04, h04);
                } else collapse(vf04);
                break;

            case R.id.VFMenu05:
                if (vf05.getVisibility() == View.GONE) {
                    expand(vf05, h05);
                } else collapse(vf05);
                break;

            case R.id.VFMenu06:
                if (vf06.getVisibility() == View.GONE) {
                    expand(vf06, h06);
                } else collapse(vf06);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, VFActivity.class));
        overridePendingTransition(R.anim.animation_leave, R.anim.hold);
    }
}