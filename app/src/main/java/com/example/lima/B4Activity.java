package com.example.lima;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;

/* B4 - Solución de conflictos */
public class B4Activity extends AppCompatActivity implements View.OnClickListener{

    Button b4_1, b4_2;
    RelativeLayout layout1, layout2;
    int h1, h2; // layout heights

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b4);

        layout1 = findViewById(R.id.b4_1layout);
        layout2 = findViewById(R.id.b4_2layout);

        b4_1 = findViewById(R.id.b4_1);
        b4_2 = findViewById(R.id.b4_2);

        b4_1.setOnClickListener(this);
        b4_2.setOnClickListener(this);

        layout1.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        layout1.getViewTreeObserver().removeOnPreDrawListener(this);
                        // Start collapsed:
                        layout1.setVisibility(View.GONE);
                        layout2.setVisibility(View.GONE);

                        // Get heights:
                        h1 = layout1.getMeasuredHeight();
                        h2 = layout2.getMeasuredHeight();
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
            case R.id.b4_1:
                if (layout1.getVisibility() == View.GONE) {
                    expand(layout1, h1);
                } else collapse(layout1);
                break;

            case R.id.b4_2:
                if (layout2.getVisibility() == View.GONE) {
                    expand(layout2, h2);
                } else collapse(layout2);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.animation_leave, R.anim.hold);
    }
}