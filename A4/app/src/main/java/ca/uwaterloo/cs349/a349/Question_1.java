package ca.uwaterloo.cs349.a349;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.RadioButton;
import java.util.ArrayList;


public class Question_1 extends AppCompatActivity {

    Button Previous_btn;
    Button next_finish;
    int point = 0;
    int questions;
    String username;
    Intent intent2;
    ArrayList<String> select = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_1);

        for(int i = 0; i < 5; ++i){
            select.add("");
        }

        next_finish = findViewById(R.id.F_N);
        Previous_btn = findViewById(R.id.previous_btn);
        Previous_btn.setEnabled(false);
        Previous_btn.setBackgroundColor(Color.LTGRAY);

        Intent intent = getIntent();
        questions = intent.getIntExtra("questions",0);
        String doQuestion ="1" + intent.getStringExtra("number");
        String button = intent.getStringExtra("btn");
        String person = "Name: "+intent.getStringExtra("user");
        username = person.substring(6);
        next_finish.setText(button);

        TextView Q = findViewById(R.id.questions);
        Q.setText(doQuestion);

        TextView userN = findViewById(R.id.userName);

        userN.setText(person);

    }

    public void logOut(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void goPreviousPage(View view){
//        Intent intent = new Intent(this,TopicSelect.class);
//        startActivity(intent);
        finish();
    }
    public int calculate(){
        int score = 0;
        if("A".equals(select.get(0))){
            score+=1;
        }
        return score;
    }

    public void goNextPage(View view){
        if(questions !=1) {
            if(intent2 == null) {
                intent2 = new Intent(this, Question_2.class);
                intent2.putExtra("user",username);
                intent2.putExtra("result",point);
                intent2.putExtra("questions",questions);
            }
            intent2.putExtra("answers",select);
            startActivityForResult(intent2,1);
        }
        else {
            Intent intent = new Intent(this, FinalScore.class);
            intent.putExtra("user",username);
            intent.putExtra("score","Your Score: /1");
            point=calculate();
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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked

        switch (view.getId()) {
            case R.id.Q1A:
                if (checked){
                    select.set(0,"A");
                    break;
                }
            case R.id.Q1B:
                if (checked){
                    select.set(0,"B");
                    break;
                }
            case R.id.Q1C:
                if (checked){
                    select.set(0,"C");
                    break;
                }
            case R.id.Q1D:
                if (checked){
                    select.set(0,"D");
                    break;
                }
        }
    }
}
