package ca.uwaterloo.cs349.a349;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;

public class Question_2 extends AppCompatActivity {

    Button Previous_btn;
    Button next_finish;
    int point;
    int questions;
    String username;
    Intent intent3;
    ArrayList<String> select = new ArrayList<>();
    String A1 ="";
    String A2 ="";
    String A3 ="";
    String A4 ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_2);

        next_finish = findViewById(R.id.F_N);
        Previous_btn = findViewById(R.id.previous_btn);
        Previous_btn.setEnabled(true);

        Intent intent = getIntent();

        String person = "Name: "+intent.getStringExtra("user");
        username = person.substring(6);
        TextView userN = findViewById(R.id.userName);
        userN.setText(person);

        select = (ArrayList<String>)intent.getExtras().getSerializable("answers");

        // choose answer

        if(select.get(1).contains("A")){
            CheckBox box1 = findViewById(R.id.Q2A);
            box1.setChecked(true);
        }
        if(select.get(1).contains("B")){
            CheckBox box2 = findViewById(R.id.Q2B);
            box2.setChecked(true);
        }
        if(select.get(1).contains("C")){
            CheckBox box3 = findViewById(R.id.Q2C);
            box3.setChecked(true);
        }
        if(select.get(1).contains("D")){
            CheckBox box4 = findViewById(R.id.Q2D);
            box4.setChecked(true);
        }
        questions = intent.getIntExtra("questions",0);

        TextView Q = findViewById(R.id.questions);
        String doQuestion = "2/"+Integer.toString(questions);
        Q.setText(doQuestion);
        if(questions > 2){
            next_finish.setText("Next");
        }else if (questions == 2){
            next_finish.setText("Finish");
        }

//        point = intent.getIntExtra("result",0);
    }

    public void logOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goPreviousPage(View view){
        Intent intent = new Intent();
        intent.putExtra("answers",select);
        setResult(RESULT_OK,intent);
//        startActivity(intent);
        finish();
    }

    public int calculate(){
        int score = 0;
        if("A".equals(select.get(0))){
            score+=1;
        }
        if("AC".equals(select.get(1))){
            score+=1;
        }
        return score;
    }

    public void goNextPage(View view){
    
        if(questions !=2) {
            if(intent3 == null) {
                intent3 = new Intent(this, Question_3.class);
                intent3.putExtra("user", username);
//                intent3.putExtra("result", point);
                intent3.putExtra("questions", questions);
            }
            intent3.putExtra("answers",select);
            startActivityForResult(intent3,1);
        }
        else {
            Intent intent = new Intent(this, FinalScore.class);
            intent.putExtra("user",username);
            intent.putExtra("score","Your Score: /2");
            point = calculate();
            intent.putExtra("result",point);
            intent.putExtra("questions",questions);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                select = (ArrayList<String>)data.getExtras().getSerializable("answers");
            }
        }
    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked

        switch(view.getId()) {
            case R.id.Q2A:
                if (checked) {
                    A1="A";
                } else{
                    A1="";
                }
                break;
            case R.id.Q2B:
                if (checked){
                    A2="B";
                } else {
                    A2="";
                }
                break;
            case R.id.Q2C:
                if (checked){
                    A3="C";
                } else {
                    A3="";
                }
                break;
            case R.id.Q2D:
                if (checked){
                    A4="D";
                } else {
                    A4="";
                }
                break;
        }
        String ans =A1+A2+A3+A4;
        select.set(1,ans);
    }
}
