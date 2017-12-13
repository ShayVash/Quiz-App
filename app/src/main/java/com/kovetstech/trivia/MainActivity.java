package com.kovetstech.trivia;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] Qdata;
    int CorrectAnswer;
    Random rnd = new Random();

    Tools t = new Tools();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetRandomQustion();
    }

    public void GetRandomQustion(){
        SetQustionData(rnd.nextInt(3) + 1);
    }
    public void SetQustionData(int number){
        String resource = "Q" + number;
        int ID = this.getResources().getIdentifier(resource, "array", getPackageName());
        Qdata = getResources().getStringArray(ID);


        ShuffleAnswers();
    }
    public void ShuffleAnswers(){
        TextView QustionView = (TextView) findViewById(R.id.Qustion);
        QustionView.setText(Qdata[0]);

        int AnsNum = rnd.nextInt(4)+1;
        CorrectAnswer = AnsNum;

        Button Current = (Button) findViewById(R.id.AnswerOne);
        for(int i = 1; i < Qdata.length-1; i++){
            if(i == 1) Current = (Button) findViewById(R.id.AnswerOne);
            if(i == 2) Current = (Button) findViewById(R.id.AnswerTwo);
            if(i == 3) Current = (Button) findViewById(R.id.AnswerThree);
            if(i == 4) Current = (Button) findViewById(R.id.AnswerFour);

            Current.setText(Qdata[i]);
            if(i == AnsNum) {
                Current.setText(Qdata[1]);
                Current = (Button) findViewById(R.id.AnswerOne);
                Current.setText(Qdata[i]);
            }
        }
    }

    public void CorrectAnim(String button){
        int ID = this.getResources().getIdentifier(t.Combine("Answer", t.DigitTOText(CorrectAnswer)), "id", getPackageName());
        final Button correct = (Button) findViewById(ID);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                correct.getBackground().setColorFilter(Color.parseColor("#4BA35D"), PorterDuff.Mode.MULTIPLY);

                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        correct.getBackground().setColorFilter(Color.parseColor("#D6D7D7"), PorterDuff.Mode.MULTIPLY);
                        GetRandomQustion();
                    }
                }, 1000);
            }
        }, 1000);
    }
    public void WrongAnim(String button){
        int ID = this.getResources().getIdentifier(t.Combine("Answer", button), "id", getPackageName());
        final Button current = (Button) findViewById(ID);
        ID = this.getResources().getIdentifier(t.Combine("Answer", t.DigitTOText(CorrectAnswer)), "id", getPackageName());
        final Button correct = (Button) findViewById(ID);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                current.getBackground().setColorFilter(Color.parseColor("#FC001A"), PorterDuff.Mode.MULTIPLY);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        correct.getBackground().setColorFilter(Color.parseColor("#4BA35D"), PorterDuff.Mode.MULTIPLY);
                        new Handler().postDelayed(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                current.getBackground().setColorFilter(Color.parseColor("#D6D7D7"), PorterDuff.Mode.MULTIPLY);
                                correct.getBackground().setColorFilter(Color.parseColor("#D6D7D7"), PorterDuff.Mode.MULTIPLY);
                                GetRandomQustion();
                            }
                        }, 1000);
                    }
                }, 1000);
            }
        }, 1500);
    }

    public void Button1(View view){
        if(CorrectAnswer == 1){
            CorrectAnim("One");
        }else WrongAnim("One");
    }
    public void Button2(View view){
        if(CorrectAnswer == 2){
            CorrectAnim("Two");
        }else WrongAnim("Two");
    }
    public void Button3(View view){
        if(CorrectAnswer == 3){
            CorrectAnim("Three");
        }else WrongAnim("Three");
    }
    public void Button4(View view){
        if(CorrectAnswer == 4){
            CorrectAnim("Four");
        }else WrongAnim("Four");
    }

}
