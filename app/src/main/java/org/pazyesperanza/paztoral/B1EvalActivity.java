package org.pazyesperanza.paztoral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

// see https://codinginflow.com/tutorials/android/quiz-app-with-sqlite/part-6-save-highscore

public class B1EvalActivity extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";

    private TextView question;
    private TextView score;
    private TextView questionCount;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button confirmNext;

    private ColorStateList textColorDefaultRb;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private int currScore;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b1_eval);

        question = findViewById(R.id.b1e_question);
        score = findViewById(R.id.b1e_score);
        questionCount = findViewById(R.id.b1e_question_count);
        radioGroup = findViewById(R.id.b1e_radio_group);
        rb1 = findViewById(R.id.b1e_rb1);
        rb2 = findViewById(R.id.b1e_rb2);
        rb3 = findViewById(R.id.b1e_rb3);
        rb4 = findViewById(R.id.b1e_rb4);
        confirmNext = findViewById(R.id.b1e_confirm);

        textColorDefaultRb = rb1.getTextColors();

        B1EvalDbHelper dbHelper = new B1EvalDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();
        //Collections.shuffle(questionList); // if want questions in random order

        showNextQuestion();
        confirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(B1EvalActivity.this, "Por favor seleccione una respuesta", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        radioGroup.clearCheck();
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            question.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            questionCounter++;
            questionCount.setText("Pregunta: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            confirmNext.setText("Confirmar");
        } else {
            finishQuiz();
        }
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNr = radioGroup.indexOfChild(rbSelected) + 1;
        if (answerNr == currentQuestion.getAnswerNum()) {
            currScore++;
            score.setText("Puntación: " + currScore);
        }
        showSolution();
    }

    private void showSolution() {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        switch (currentQuestion.getAnswerNum()) {
            case 1:
                rb1.setTextColor(Color.GREEN);
                question.setText("Respuesta 1 es correcta");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                question.setText("Respuesta 2 es correcta");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                question.setText("Respuesta 3 es correcta");
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                question.setText("Respuesta 4 es correcta");
                break;
        }
        if (questionCounter < questionCountTotal) {
            confirmNext.setText("Próximo");
        } else {
            confirmNext.setText("Finalizar");
        }
    }
    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, currScore);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

}