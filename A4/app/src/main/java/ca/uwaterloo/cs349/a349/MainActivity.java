package ca.uwaterloo.cs349.a349;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button next;
    EditText edit;
    String personName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next = findViewById(R.id.Next_btn);
        next.setEnabled(false);

        edit = findViewById(R.id.TextName);

        edit.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s){
                personName = edit.getText().toString();
                if(s.length()== 0){
                   next.setEnabled(false);
                   next.setBackgroundColor(Color.LTGRAY);
                } else {
                    next.setEnabled(true);
                    next.setBackgroundColor(Color.YELLOW);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int after, int count){
                if(s.length()==0){
                    next.setEnabled(false);
                    next.setBackgroundColor(Color.LTGRAY);
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int brfore, int count){
                if(s.length() == 0){
                    next.setEnabled(false);
                    next.setBackgroundColor(Color.LTGRAY);
                }else{
                    next.setEnabled(true);
                    next.setBackgroundColor(Color.YELLOW);
                }
            }
        });
    }

    public void topicPage(View view){
        if(personName != null) {
            Intent intent = new Intent(this, TopicSelect.class);
            intent.putExtra("Name", personName);
            startActivity(intent);
        }
    }
}
