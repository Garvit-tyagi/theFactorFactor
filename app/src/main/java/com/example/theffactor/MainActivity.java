package com.example.theffactor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int numberEntered;
  int maxScore;
  int yourScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null){
            yourScore=savedInstanceState.getInt("yourScore");
            maxScore=savedInstanceState.getInt("maxScore");
        }

    }

    public void factor(View view){
        boolean valid=true;

        try{
            EditText number=(EditText)findViewById(R.id.number);

            numberEntered= Integer.parseInt(number.getText().toString());}
        catch(NumberFormatException e){
    valid=false;
         }


        Intent i= new Intent(this,Main2Activity.class);

        i.putExtra("numberEntered",numberEntered);
        i.putExtra("maxScore",maxScore);
        i.putExtra("yourScore",yourScore);
   if ( valid) {
       if (numberEntered == 0) {
           Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
       } else if (numberEntered == 1) {
           Toast.makeText(this, "1 has only 1 as a factor", Toast.LENGTH_SHORT).show();
       } else if (numberEntered == 2) {
           Toast.makeText(this, "2 has only 1 and 2 as a factor\nenter number greater than 2", Toast.LENGTH_LONG).show();
       }else if(numberEntered >1000000){
           Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
       }
        else {
           startActivity(i);
       }
   }else{
       Vibrator vibe=(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
       vibe.vibrate(80);
       Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();



   }



    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
        SharedPreferences myScore=getSharedPreferences("myAwesomeScore", Context.MODE_PRIVATE);
        yourScore=myScore.getInt("yourScore",0);
        TextView txt=(TextView)findViewById(R.id.yourScore);
        txt.setText(Integer.toString(yourScore));
        SharedPreferences myMaxScore=getSharedPreferences("myAwesomeMaxScore",Context.MODE_PRIVATE);
        maxScore=myMaxScore.getInt("maxScore",0);
        TextView txt1=(TextView)findViewById(R.id.highScoreValue);
        txt1.setText(Integer.toString(maxScore));
    }
    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences myScore=getSharedPreferences("myAwesomeScore", Context.MODE_PRIVATE);
        myScore.edit().clear().commit();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("yourScore",yourScore);
        outState.putInt("maxScore",maxScore);
    }
}
