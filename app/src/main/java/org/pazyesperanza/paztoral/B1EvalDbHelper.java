package org.pazyesperanza.paztoral;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

// see https://codinginflow.com/tutorials/android/quiz-app-with-sqlite/part-6-save-highscore

public class B1EvalDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "B1Eval.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;

    private SQLiteDatabase db;

    public B1EvalDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                EvalContract.QuestionsTable.TABLE_NAME + " ( " +
                EvalContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EvalContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                EvalContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                EvalContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                EvalContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                EvalContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                EvalContract.QuestionsTable.COLUMN_ANSWER_NUM + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EvalContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    // The ten questions, their options, and the correct answer to each
    private void fillQuestionsTable() {
        Question q01 = new Question(context.getString(R.string.b1e_1),
                context.getString(R.string.b1e_1a), context.getString(R.string.b1e_1b),
                context.getString(R.string.b1e_1c), context.getString(R.string.b1e_1d), 3);
        addQuestion(q01);

        Question q02 = new Question(context.getString(R.string.b1e_2),
                context.getString(R.string.b1e_2a), context.getString(R.string.b1e_2b),
                context.getString(R.string.b1e_2c), context.getString(R.string.b1e_2d), 2);
        addQuestion(q02);

        Question q03 = new Question(context.getString(R.string.b1e_3),
                context.getString(R.string.b1e_3a), context.getString(R.string.b1e_3b),
                context.getString(R.string.b1e_3c), context.getString(R.string.b1e_3d), 3);
        addQuestion(q03);

        Question q04 = new Question(context.getString(R.string.b1e_4),
                context.getString(R.string.b1e_4a), context.getString(R.string.b1e_4b),
                context.getString(R.string.b1e_4c), context.getString(R.string.b1e_4d), 1);
        addQuestion(q04);

        Question q05 = new Question(context.getString(R.string.b1e_5),
                context.getString(R.string.b1e_5a), context.getString(R.string.b1e_5b),
                context.getString(R.string.b1e_5c), context.getString(R.string.b1e_5d), 3);
        addQuestion(q05);

        Question q06 = new Question(context.getString(R.string.b1e_6),
                context.getString(R.string.b1e_6a), context.getString(R.string.b1e_6b),
                context.getString(R.string.b1e_6c), context.getString(R.string.b1e_6d), 1);
        addQuestion(q06);

        Question q07 = new Question(context.getString(R.string.b1e_7),
                context.getString(R.string.b1e_7a), context.getString(R.string.b1e_7b),
                context.getString(R.string.b1e_7c), context.getString(R.string.b1e_7d), 1);
        addQuestion(q07);

        Question q08 = new Question(context.getString(R.string.b1e_8),
                context.getString(R.string.b1e_8a), context.getString(R.string.b1e_8b),
                context.getString(R.string.b1e_8c), context.getString(R.string.b1e_8d), 1);
        addQuestion(q08);

        Question q09 = new Question(context.getString(R.string.b1e_9),
                context.getString(R.string.b1e_9a), context.getString(R.string.b1e_9b),
                context.getString(R.string.b1e_9c), context.getString(R.string.b1e_9d), 1);
        addQuestion(q09);

        Question q10 = new Question(context.getString(R.string.b1e_10),
                context.getString(R.string.b1e_10a), context.getString(R.string.b1e_10b),
                context.getString(R.string.b1e_10c), context.getString(R.string.b1e_10d), 1);
        addQuestion(q10);
    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(EvalContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(EvalContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(EvalContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(EvalContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(EvalContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(EvalContract.QuestionsTable.COLUMN_ANSWER_NUM, question.getAnswerNum());
        db.insert(EvalContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + EvalContract.QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(EvalContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(EvalContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(EvalContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(EvalContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNum(c.getInt(c.getColumnIndex(EvalContract.QuestionsTable.COLUMN_ANSWER_NUM)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

}
