package com.example.mystack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Delete_series_activity extends AppCompatActivity {
    private MyDatabaseHelper_series myDatabaseHelper;
    Button delete_button;
    EditText given_ID;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_movie);
        //openDialogue();

        myDatabaseHelper = new MyDatabaseHelper_series(this);

        delete_button = findViewById(R.id.deleteDataButtonID);
        given_ID = findViewById(R.id.givenIDdeleted);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = given_ID.getText().toString();
                myDatabaseHelper.deleteData(id);
                Toast.makeText(Delete_series_activity.this, "Entry deleted, if it existed! :3", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
