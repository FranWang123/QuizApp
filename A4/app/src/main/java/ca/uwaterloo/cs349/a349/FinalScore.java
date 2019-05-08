package ca.uwaterloo.cs349.a349;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FinalScore extends AppCompatActivity {
    String personName;
    String scores;
    int point;
    int questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        Intent intent = getIntent();
        String person = "Name: "+intent.getStringExtra("user");
        TextView userN = findViewById(R.id.userName);
        userN.setText(person);

        point = intent.getIntExtra("result",0);
        scores = intent.getStringExtra("score").substring(0,12)+Integer.toString(point)+intent.getStringExtra("score").substring(12);


        TextView final_result = findViewById(R.id.results);
        final_result.setText(scores);

        //check the number of questions the user chose in topic selection and show the corresponding number of answers
        questions = intent.getIntExtra("questions",1);
        if(questions >= 1){
            TextView P1 = findViewById(R.id.ans_sel1);
            P1.setText("Q1: Canada");
            ImageView I1 = findViewById(R.id.ans_ima1);
            I1.setImageResource(R.drawable.image1);
        }
        if(questions >= 2){
            TextView P2 = findViewById(R.id.ans_sel2);
            P2.setText("Q2: Brazil  &  Argentina");
            ImageView I2 = findViewById(R.id.ans_ima2);
            I2.setImageResource(R.drawable.image2);
        }
        if(questions >= 3){
            TextView P3 = findViewById(R.id.ans_sel3);
            P3.setText("Q3:China");
            ImageView I3 = findViewById(R.id.ans_ima3);
            I3.setImageResource(R.drawable.image3);
        }
        if (questions >=4){
            TextView P4 = findViewById(R.id.ans_sel4);
            P4.setText("Q4: South Korea");
            ImageView I4 = findViewById(R.id.ans_ima4);
            I4.setImageResource(R.drawable.image4);
        }
        if(questions >= 5){
            TextView P5 = findViewById(R.id.ans_sel5);
            P5.setText("Q5: South Africa  &  United Kingdom");
            ImageView I5 = findViewById(R.id.ans_ima5);
            I5.setImageResource(R.drawable.image5);
        }
    }
    
    //return welcome page
    public void logOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    
    //return topic selection
    public void goTopicSelection(View view){
        Intent intent = new Intent(this, TopicSelect.class);
        TextView t= findViewById(R.id.userName);
        personName = t.getText().toString().substring(6);
        intent.putExtra("Name",personName);
        startActivity(intent);
    }
}
