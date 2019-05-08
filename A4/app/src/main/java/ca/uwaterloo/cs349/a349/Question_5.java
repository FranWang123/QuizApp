package ca.uwaterloo.cs349.a349;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class Question_5 extends AppCompatActivity {

    Button Previous_btn;
    Button next_finish;
    int point;
    String username;
    int questions;
    ArrayList<String> select = new ArrayList<>();
    String A1 ="";
    String A2 ="";
    String A3 ="";
    String A4 ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_5);


        next_finish = findViewById(R.id.F_N);
        Previous_btn = findViewById(R.id.previous_btn);
        Previous_btn.setEnabled(true);

        Intent intent = getIntent();
        questions = intent.getIntExtra("questions",0);
        select = (ArrayList<String>)intent.getExtras().getSerializable("answers");

        if(select.get(4).contains("A")){
            CheckBox box1 = findViewById(R.id.Q5A);
            box1.setChecked(true);
        }
        if(select.get(4).contains("B")){
            CheckBox box2 = findViewById(R.id.Q5B);
            box2.setChecked(true);
        }
        if(select.get(4).contains("C")){
            CheckBox box3 = findViewById(R.id.Q5C);
            box3.setChecked(true);
        }
        if(select.get(4).contains("D")){
            CheckBox box4 = findViewById(R.id.Q5D);
            box4.setChecked(true);
        }

        String person = "Name: "+intent.getStringExtra("user");
        username = person.substring(6);
        TextView userN = findViewById(R.id.userName);
        userN.setText(person);

        next_finish.setText("Finish");

//        point = intent.getIntExtra("result",0);

        TextView Q = findViewById(R.id.questions);
        Q.setText("5/5");
    }

    public void logOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goPreviousPage(View view){
//        Intent intent = new Intent(this,TopicSelect.class);
//        startActivity(intent);
        Intent intent = new Intent();
        intent.putExtra("answers",select);
        setResult(RESULT_OK,intent);
        finish();
    }

    // calcualte the scores that the user get
    public int calculate(){
        int score = 0;
        if("A".equals(select.get(0))){
            score+=1;
        }
        if("AC".equals(select.get(1))){
            score+=1;
        }
        if("C".equals(select.get(2))){
            score+=1;
        }
        if("D".equals(select.get(3))){
            score+=1;
        }
        if("CD".equals(select.get(4))){
            score+=1;
        }

        return score;
    }
    public void goNextPage(View view){

        Intent intent = new Intent(this, FinalScore.class);
        intent.putExtra("user",username);
        intent.putExtra("score","Your Score: /5");
        point = calculate();
        intent.putExtra("result",point);
        intent.putExtra("questions",questions);
        startActivity(intent);
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked

        switch(view.getId()) {
            case R.id.Q5A:
                if (checked) {
                    A1="A";
                } else{
                    A1="";
                }
                break;
            case R.id.Q5B:
                if (checked){
                    A2="B";
                } else {
                    A2="";
                }
                break;
            case R.id.Q5C:
                if (checked){
                    A3="C";
                } else {
                    A3="";
                }
                break;
            case R.id.Q5D:
                if (checked){
                    A4="D";
                } else {
                    A4="";
                }
                break;
        }
        String ans =A1+A2+A3+A4;
        select.set(4,ans);
    }
}
