package ca.uwaterloo.cs349.a349;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.jar.Attributes;

public class TopicSelect extends AppCompatActivity {
    Spinner spinner;
    int questions;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_select);

        Intent intent = getIntent();
        username =intent.getStringExtra("Name");

        TextView welcomeView = findViewById(R.id.welcome_name);
        welcomeView.setText(username);

        spinner = findViewById(R.id.spinner);

    }
    public void logOut(View view) {
        finish();
    }

    public void loadQuestion(View view){
        questions = Integer.parseInt(spinner.getSelectedItem().toString());
        Intent intent = new Intent(this, Question_1.class);
        intent.putExtra("user",username);
        intent.putExtra("questions",questions);
        switch (questions) {
            case 1:
                intent.putExtra("btn","Finish");
                intent.putExtra("number","/1");
                break;
            case 2:
                intent.putExtra("btn","Next");
                intent.putExtra("number","/2");
                break;
            case 3:
                intent.putExtra("btn","Next");
                intent.putExtra("number","/3");
                break;
            case 4:
                intent.putExtra("btn","Next");
                intent.putExtra("number","/4");
                break;
            case 5:
                intent.putExtra("btn","Next");
                intent.putExtra("number","/5");
                break;
            default:
                intent.putExtra("btn","Finish");
                intent.putExtra("number","/1");
                break;
        }
        startActivityForResult(intent,1);
    }
}
