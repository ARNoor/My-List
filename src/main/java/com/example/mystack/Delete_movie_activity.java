package com.example.mystack;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Delete_movie_activity extends AppCompatActivity {
    private MyDatabaseHelper myDatabaseHelper;
    Button delete_button;
    EditText given_ID;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_movie);
        //openDialogue();

        myDatabaseHelper = new MyDatabaseHelper(this);

        delete_button = findViewById(R.id.deleteDataButtonID);
        given_ID = findViewById(R.id.givenIDdeleted);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = given_ID.getText().toString();
                myDatabaseHelper.deleteData(id);
                Toast.makeText(Delete_movie_activity.this, "Entry deleted, if it existed! :3", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
