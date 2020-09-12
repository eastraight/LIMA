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

/* VF1 - Violencia en la pareja */
public class VF1Activity extends AppCompatActivity implements View.OnClickListener {
    Button vfm11, vfm12, vfm13, vfm14, vfm15, vfm16;
    RelativeLayout vf11, vf12, vf13, vf14, vf15, vf16;
    int h11, h12, h13, h14, h15, h16; // heights

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_f_1);

        vf11 = findViewById(R.id.VF11);
        vf12 = findViewById(R.id.VF12);
        vf13 = findViewById(R.id.VF13);
        vf14 = findViewById(R.id.VF14);
        vf15 = findViewById(R.id.VF15);
        vf16 = findViewById(R.id.VF16);

        vfm11 = findViewById(R.id.VFMenu11);
        vfm12 = findViewById(R.id.VFMenu12);
        vfm13 = findViewById(R.id.VFMenu13);
        vfm14 = findViewById(R.id.VFMenu14);
        vfm15 = findViewById(R.id.VFMenu15);
        vfm16 = findViewById(R.id.VFMenu16);

        vfm11.setOnClickListener(this);
        vfm12.setOnClickListener(this);
        vfm13.setOnClickListener(this);
        vfm14.setOnClickListener(this);
        vfm15.setOnClickListener(this);
        vfm16.setOnClickListener(this);

        vf11.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        vf11.getViewTreeObserver().removeOnPreDrawListener(this);
                        // Start collapsed:
                        vf11.setVisibility(View.GONE);
                        vf12.setVisibility(View.GONE);
                        vf13.setVisibility(View.GONE);
                        vf14.setVisibility(View.GONE);
                        vf15.setVisibility(View.GONE);
                        vf16.setVisibility(View.GONE);

                        // Get heights:
                        h11 = vf11.getMeasuredHeight();
                        h12 = vf12.getMeasuredHeight();
                        h13 = vf13.getMeasuredHeight();
                        h14 = vf14.getMeasuredHeight();
                        h15 = vf15.getMeasuredHeight();
                        h16 = vf16.getMeasuredHeight();
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
            case R.id.VFMenu11:
                if (vf11.getVisibility() == View.GONE) {
                    expand(vf11, h11);
                } else collapse(vf11);
                break;

            case R.id.VFMenu12:
                if (vf12.getVisibility() == View.GONE) {
                    expand(vf12, h12);
                } else collapse(vf12);
                break;

            case R.id.VFMenu13:
                if (vf13.getVisibility() == View.GONE) {
                    expand(vf13, h13);
                } else collapse(vf13);
                break;

            case R.id.VFMenu14:
                if (vf14.getVisibility() == View.GONE) {
                    expand(vf14, h14);
                } else collapse(vf14);
                break;

            case R.id.VFMenu15:
                if (vf15.getVisibility() == View.GONE) {
                    expand(vf15, h15);
                } else collapse(vf15);
                break;

            case R.id.VFMenu16:
                if (vf16.getVisibility() == View.GONE) {
                    expand(vf16, h16);
                } else collapse(vf16);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, VFActivity.class));
        overridePendingTransition(R.anim.animation_leave, R.anim.hold);
    }
}