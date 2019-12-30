package com.example.mystack;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
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
import android.widget.TextView;
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

public class Add_movie_activity extends AppCompatActivity {

    EditText movieName;
    EditText suggestedBy;
    Button addMovie;
    String movie_genre;
    String movie_rating;

    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie);

        movieName = findViewById(R.id.movie_name);
        suggestedBy = findViewById(R.id.suggestedBy);
        addMovie = findViewById(R.id.add_movie_button);
        myDatabaseHelper = new MyDatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        addMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMovie();
            }
        });

        Spinner spinner = findViewById(R.id.spinner);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Not specified");
        arrayList.add("Action");
        arrayList.add("Biography");
        arrayList.add("Comedy");
        arrayList.add("Horror");
        arrayList.add("Romance");
        arrayList.add("Thriller");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                movie_genre = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + book_genre,          Toast.LENGTH_LONG).show();
                //genre = movie_genre;
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });


        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("Must watch!!");
        arrayList2.add("Interested");
        arrayList2.add("Okay");
        arrayList2.add("We'll see");

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                movie_rating = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + book_genre,          Toast.LENGTH_LONG).show();
                //genre = movie_genre;
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }

    private void addMovie(){
        String movie = movieName.getText().toString();
        String genre = movie_genre;
        String suggested = suggestedBy.getText().toString();
        String rating = movie_rating;

        long row_id = myDatabaseHelper.insertData(movie, genre, suggested, rating);
        if(row_id < 0){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
