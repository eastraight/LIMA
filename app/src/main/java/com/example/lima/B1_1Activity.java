package com.example.lima;

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

//TODO: refactor b1_Menu1 IDs

/* B1_1 - Violencia en la pareja */
public class B1_1Activity extends AppCompatActivity implements View.OnClickListener {
    Button b1_m11, b1_m12, b1_m13, b1_m14, b1_m15, b1_m16;
    RelativeLayout b1_11, b1_12, b1_13, b1_14, b1_15, b1_16;
    int h11, h12, h13, h14, h15, h16; // heights

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1_2);

        b1_11 = findViewById(R.id.b1_11);
        b1_12 = findViewById(R.id.b1_12);
        b1_13 = findViewById(R.id.b1_13);
        b1_14 = findViewById(R.id.b1_14);
        b1_15 = findViewById(R.id.b1_15);
        b1_16 = findViewById(R.id.b1_16);

        b1_m11 = findViewById(R.id.b1_Menu11);
        b1_m12 = findViewById(R.id.b1_Menu12);
        b1_m13 = findViewById(R.id.b1_Menu13);
        b1_m14 = findViewById(R.id.b1_Menu14);
        b1_m15 = findViewById(R.id.b1_Menu15);
        b1_m16 = findViewById(R.id.b1_Menu16);

        b1_m11.setOnClickListener(this);
        b1_m12.setOnClickListener(this);
        b1_m13.setOnClickListener(this);
        b1_m14.setOnClickListener(this);
        b1_m15.setOnClickListener(this);
        b1_m16.setOnClickListener(this);

        b1_11.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        b1_11.getViewTreeObserver().removeOnPreDrawListener(this);
                        // Start collapsed:
                        b1_11.setVisibility(View.GONE);
                        b1_12.setVisibility(View.GONE);
                        b1_13.setVisibility(View.GONE);
                        b1_14.setVisibility(View.GONE);
                        b1_15.setVisibility(View.GONE);
                        b1_16.setVisibility(View.GONE);

                        // Get heights:
                        h11 = b1_11.getMeasuredHeight();
                        h12 = b1_12.getMeasuredHeight();
                        h13 = b1_13.getMeasuredHeight();
                        h14 = b1_14.getMeasuredHeight();
                        h15 = b1_15.getMeasuredHeight();
                        h16 = b1_16.getMeasuredHeight();
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
            case R.id.b1_Menu11:
                if (b1_11.getVisibility() == View.GONE) {
                    expand(b1_11, h11);
                } else collapse(b1_11);
                break;

            case R.id.b1_Menu12:
                if (b1_12.getVisibility() == View.GONE) {
                    expand(b1_12, h12);
                } else collapse(b1_12);
                break;

            case R.id.b1_Menu13:
                if (b1_13.getVisibility() == View.GONE) {
                    expand(b1_13, h13);
                } else collapse(b1_13);
                break;

            case R.id.b1_Menu14:
                if (b1_14.getVisibility() == View.GONE) {
                    expand(b1_14, h14);
                } else collapse(b1_14);
                break;

            case R.id.b1_Menu15:
                if (b1_15.getVisibility() == View.GONE) {
                    expand(b1_15, h15);
                } else collapse(b1_15);
                break;

            case R.id.b1_Menu16:
                if (b1_16.getVisibility() == View.GONE) {
                    expand(b1_16, h16);
                } else collapse(b1_16);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, B1Activity.class));
        overridePendingTransition(R.anim.animation_leave, R.anim.hold);
    }
}