package com.example.theffactor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.lang.String;

public class Main2Activity extends AppCompatActivity {
    Button option1,option2,option3;
    int answer,index,index2,index3,numberEntered;
    int yourScore=0,maxScore;
    Random r,r2;
    String option1Text,option2Text,option3Text;
    CountDownTimer countDownTimer;

    long millisUntilFinished;
    int time=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
      final  TextView timer=(TextView)findViewById(R.id.timer);

      if(savedInstanceState!=null){
          option1Text=savedInstanceState.getString("option1Text");
          option2Text=savedInstanceState.getString("option2Text");
          option3Text=savedInstanceState.getString("option3Text");
          answer=savedInstanceState.getInt("answer");

          time=savedInstanceState.getInt("time");
      }


       countDownTimer=new CountDownTimer(time*1000, 1000) {

            public void onTick(long millisLeft) {
                  millisUntilFinished=millisLeft;
                timer.setText("time remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                yourScore=0;
                Toast.makeText(Main2Activity.this, "Wrong Answer\nCorrect Answer is "+answer, Toast.LENGTH_SHORT).show();
                Vibrator vibe=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(80);
                goback();
            }
        }.start();


        Intent incomingIntent= getIntent();
        Bundle bundle=incomingIntent.getExtras();
        if(bundle!=null){
            numberEntered=bundle.getInt("numberEntered")  ;
            yourScore=bundle.getInt("yourScore");
            maxScore=bundle.getInt("maxScore");
        }



        final LinearLayout rootView=(LinearLayout)findViewById(R.id.rootview);


        option1=(Button)findViewById(R.id.buttonOne);
        option2=(Button)findViewById(R.id.buttonTwo);
        option3=(Button)findViewById(R.id.buttonThree);










        TextView question=(TextView)findViewById(R.id.question);
        question.setText("Which of the following is the factor of "+numberEntered);




        ArrayList<Integer> factors=new ArrayList<Integer>();
        for(int i=1;i<=numberEntered;i++){
            if ( numberEntered%i==0){
                factors.add(i);
            }
        }

        ArrayList<Integer> nonfactors=new ArrayList<Integer>();
        for(int i=1;i<=numberEntered;i++){
            if ( numberEntered%i!=0){
                nonfactors.add(i);
            }
        }



         r=new Random();
        index=r.nextInt(factors.size());
         r2=new Random();
        index2=r2.nextInt(nonfactors.size());
        index3=r2.nextInt(nonfactors.size());





        ArrayList<Integer> options=new ArrayList<Integer>();
        options.add(factors.get(index));
        options.add(nonfactors.get(index2));
        options.add(nonfactors.get(index3));
        Collections.shuffle(options);

        answer=factors.get(index);
        option1.setText(Integer.toString(options.get(0)));
        option2.setText(Integer.toString(options.get(1)));
        option3.setText(Integer.toString(options.get(2)));

         option1Text= option1.getText().toString();
         option2Text= option2.getText().toString();
         option3Text= option3.getText().toString();

         option1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(Integer.parseInt(option1Text)==answer){
                     yourScore=yourScore+1;
                     Toast.makeText(Main2Activity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                     rootView.setBackgroundColor(Color.parseColor("#76FF03"));

                     goback();
                 }
                 else{ yourScore=0;
                     Toast.makeText(Main2Activity.this, "Wrong Answer\nCorrect Answer is "+answer, Toast.LENGTH_SHORT).show();
                     rootView.setBackgroundColor(Color.parseColor("#D50000"));
                     Vibrator vibe=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                     vibe.vibrate(80);
                    goback();
                 }

             }
         });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(option2Text)==answer){
                    yourScore=yourScore+1;
                    Toast.makeText(Main2Activity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    rootView.setBackgroundColor(Color.parseColor("#76FF03"));
                    goback();
                }
                else{ yourScore=0;
                    Toast.makeText(Main2Activity.this, "Wrong Answer\nCorrect Answer is "+answer, Toast.LENGTH_SHORT).show();
                    rootView.setBackgroundColor(Color.parseColor("#D50000"));
                    Vibrator vibe=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(80);
                    goback();
                }

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(option3Text)==answer){
                    yourScore=yourScore+1;
                    Toast.makeText(Main2Activity.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                    rootView.setBackgroundColor(Color.parseColor("#76FF03"));
                    goback();
                }
                else{
                    yourScore=0;
                    Toast.makeText(Main2Activity.this, "Wrong Answer\nCorrect Answer is "+answer, Toast.LENGTH_SHORT).show();
                    rootView.setBackgroundColor(Color.parseColor("#D50000"));
                    Vibrator vibe=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(80);
                    goback();
                }

            }
        });




    }
    public void goback(){
        SharedPreferences myScore=getSharedPreferences("myAwesomeScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=myScore.edit();
        editor.putInt("yourScore",yourScore);
        editor.commit();

        if(yourScore>maxScore){
            maxScore=yourScore;
            SharedPreferences myMaxScore=getSharedPreferences("myAwesomeMaxScore",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor1=myMaxScore.edit();
            editor1.putInt("maxScore",maxScore);
            editor1.commit();
        }
        finish();
    }

    @Override
    protected void onSaveInstanceState( @NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("answer",answer);
        outState.putString("option1Text",option1Text);
        outState.putString("option2Text",option2Text);
        outState.putString("option3Text",option3Text);

        outState.putInt("time",time);
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.cancel();
    }
}













