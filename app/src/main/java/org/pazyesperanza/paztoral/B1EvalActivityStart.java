package org.pazyesperanza.paztoral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// see https://codinginflow.com/tutorials/android/quiz-app-with-sqlite/part-6-save-highscore

public class B1EvalActivityStart extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String PUNTACION_ALTA = "puntacionAlta";
    private TextView textViewHighscore;
    private int puntAlta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1_eval_start);

        textViewHighscore = findViewById(R.id.b1e_highscore);
        loadHighscore();
        Button buttonStartQuiz = findViewById(R.id.b1e_start);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Intent intent = new Intent(B1EvalActivityStart.this, B1EvalActivity.class);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(B1EvalActivity.EXTRA_SCORE, 0);
                if (score > puntAlta) {
                    updateHighscore(score);
                }
            }
        }
    }
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        puntAlta = prefs.getInt(PUNTACION_ALTA, 0);
        textViewHighscore.setText("Puntación alta: " + puntAlta);
    }
    private void updateHighscore(int highscoreNew) {
        puntAlta = highscoreNew;
        textViewHighscore.setText("Puntación alta: " + puntAlta);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PUNTACION_ALTA, puntAlta);
        editor.apply();
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