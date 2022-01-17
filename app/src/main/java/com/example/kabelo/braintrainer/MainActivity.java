package com.example.kabelo.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView num1TextView;
    TextView num2TextView;
    TextView num3TextView;
    TextView num4TextView;
    TextView scoreTextView;
    TextView taskTextView;
    TextView timerTextView;
    TextView resultsTextView;
    Button playAgainBtn;
    int maxNum;
    int correctTag;
    String resultString;
    int roundsPlayed = 0;
    int roundsWon = 0;
    int timer = 30;
    CountDownTimer countDownTimer;

    public int generateRandNum(int max)
    {
        int randNum = new Random().nextInt(max+1);

        return randNum;
    }

    public void generateNumbers ()
    {

        maxNum = 20;
        int randNum1 = generateRandNum(maxNum);
        int randNum2 = generateRandNum(maxNum);
        correctTag = generateRandNum(4);

        int result = randNum1 + randNum2;
        resultString = Integer.toString(result);

        taskTextView.setText(Integer.toString(randNum1)+" + "+Integer.toString(randNum2));

        if (Integer.parseInt(num1TextView.getTag().toString()) == correctTag) {
            num1TextView.setText(resultString);
            num2TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
            num3TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
            num4TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
        }else if (Integer.parseInt(num2TextView.getTag().toString()) == correctTag) {
            num2TextView.setText(resultString);
            num1TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
            num3TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
            num4TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
        }else if (Integer.parseInt(num3TextView.getTag().toString()) == correctTag) {
            num3TextView.setText(resultString);
            num2TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
            num1TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
            num4TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
        }else if (Integer.parseInt(num4TextView.getTag().toString()) == correctTag){
            num4TextView.setText(resultString);
            num2TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
            num3TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
            num1TextView.setText(Integer.toString(generateRandNum(maxNum + 20)));
        }
    }

    public void numClickFunc(View view)
    {
        Log.i("Num tag","here");
        resultsTextView.setVisibility(View.VISIBLE);

        TextView counter = (TextView) view;
        int tappedTag = Integer.parseInt(counter.getTag().toString());
        
        if (tappedTag == correctTag)
        {
            roundsWon++;
            roundsPlayed++;
            resultsTextView.setText("CORRECT!");
        }else {
            roundsPlayed++;
            resultsTextView.setText("WRONG!");
        }

        scoreTextView.setText(Integer.toString(roundsWon)+"/"+Integer.toString(roundsPlayed));
        generateNumbers();
    }

    public void resetGame(){

        roundsPlayed = 0;
        roundsWon = 0;
        timer = 30;
        countDownTimer.start();
        playAgainBtn.setVisibility(View.VISIBLE);
        num1TextView.setEnabled(true);
        num2TextView.setEnabled(true);
        num3TextView.setEnabled(true);
        num4TextView.setEnabled(true);
        resultsTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setText("0/0");

    }

    public void playAgainFunc (View view){

        resetGame();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1TextView = findViewById(R.id.num1TextView);
        num2TextView = findViewById(R.id.num2TextView);
        num3TextView = findViewById(R.id.num3TextView);
        num4TextView = findViewById(R.id.num4TextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        taskTextView = findViewById(R.id.taskTextView);
        timerTextView = findViewById(R.id.timerTextView);
        resultsTextView = findViewById(R.id.resultsTextView);
        playAgainBtn = findViewById(R.id.playAgainBtn);

        generateNumbers();


        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timer--;
                timerTextView.setText(Integer.toString(timer) + "s");
            }

            @Override
            public void onFinish() {

                playAgainBtn.setVisibility(View.VISIBLE);
                num1TextView.setEnabled(false);
                num2TextView.setEnabled(false);
                num3TextView.setEnabled(false);
                num4TextView.setEnabled(false);
                resultsTextView.setText("Done");

            }
        }.start();

    }
}
