package com.example.lastedition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends EditText title_input,transport_input, date_input, returndate_input, description_input, participant_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        transport_input = findViewById(R.id.transport_input);
        date_input = findViewById(R.id.date_input);
        returndate_input = findViewById(R.id.returndate_input);
        description_input = findViewById(R.id.description_input);
        participant_input = findViewById(R.id.participant_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                com.example.lastdemo.MyDatabaseHelper myDB = new com.example.lastdemo.MyDatabaseHelper(AddActivity.this);
                myDB.addTrip(title_input.getText().toString().trim(),
                        transport_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        returndate_input.getText().toString().trim(),
                        description_input.getText().toString().trim(),
                        Integer.valueOf(participant_input.getText().toString().trim()));
            }
        });
    }
}
