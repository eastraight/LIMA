package com.example.lima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class B1EvalActivity extends AppCompatActivity {

    private TextView question;
    private TextView score;
    private TextView questionCount;
    private RadioGroup radioGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button confirmNext;

    private List<Question> questionList;

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
        B1EvalDbHelper dbHelper = new B1EvalDbHelper(this);
        questionList = dbHelper.getAllQuestions();
    }
}