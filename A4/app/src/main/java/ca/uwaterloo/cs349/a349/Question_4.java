package ca.uwaterloo.cs349.a349;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Question_4 extends AppCompatActivity {

    Button Previous_btn;
    Button next_finish;
    int point;
    int questions;
    String username;
    Intent intent5;
    ArrayList<String> select = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_4);


        next_finish = findViewById(R.id.F_N);
        Previous_btn = findViewById(R.id.previous_btn);
        Previous_btn.setEnabled(true);

        Intent intent = getIntent();

        String person = "Name: "+intent.getStringExtra("user");
        username = person.substring(6);
        TextView userN = findViewById(R.id.userName);
        userN.setText(person);

        select = (ArrayList<String>)intent.getExtras().getSerializable("answers");

        if(select.get(3).contains("A")){
            RadioButton b1 = findViewById(R.id.Q4A);
            b1.setChecked(true);
        }
        if(select.get(3).contains("B")){
            RadioButton b2 = findViewById(R.id.Q4B);
            b2.setChecked(true);
        }
        if(select.get(3).contains("C")){
            RadioButton b3 = findViewById(R.id.Q4C);
            b3.setChecked(true);
        }
        if(select.get(3).contains("D")){
            RadioButton b4 = findViewById(R.id.Q4D);
            b4.setChecked(true);
        }
        questions = intent.getIntExtra("questions",0);

        TextView Q = findViewById(R.id.questions);
        String doQuestion = "4/"+Integer.toString(questions);
        Q.setText(doQuestion);
        if(questions > 4){
            next_finish.setText("Next");
        }else if (questions==4){
            next_finish.setText("Finish");
        }

    }

    public void logOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goPreviousPage(View view){
        Intent intent = new Intent();
        intent.putExtra("answers",select);
        setResult(RESULT_OK,intent);
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
        if("C".equals(select.get(2))){
            score+=1;
        }
        if("D".equals(select.get(3))){
            score+=1;
        }
        return score;
    }
    public void goNextPage(View view){
        if(questions !=4) {
            if(intent5 == null) {
                intent5 = new Intent(this, Question_5.class);
                intent5.putExtra("user", username);
                intent5.putExtra("questions", questions);
            }
            intent5.putExtra("answers",select);
            startActivityForResult(intent5,1);
        }
        else {
            Intent intent = new Intent(this, FinalScore.class);
            intent.putExtra("user",username);
            intent.putExtra("score","Your Score: /4");
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
                select = (ArrayList<String>)data.getExtras().getSerializable("answers");;
            }
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.Q4A:
                if (checked){
                    select.set(3,"A");
                    break;
                }
            case R.id.Q4B:
                if (checked){
                    select.set(3,"B");
                    break;
                }
            case R.id.Q4C:
                if (checked){
                    select.set(3,"C");
                    break;
                }
            case R.id.Q4D:
                if (checked){
                    select.set(3,"D");
                    break;
                }
        }
    }
}
