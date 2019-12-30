package com.example.mystack;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Add_series_activity extends AppCompatActivity {

    EditText seriesName;
    EditText suggestedBy;
    Button addSeries;
    String series_genre;
    String series_rating;

    MyDatabaseHelper_series myDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_series);

        seriesName = findViewById(R.id.series_name_ID);
        suggestedBy = findViewById(R.id.suggested_by_series_ID);
        addSeries = findViewById(R.id.add_series_B);
        myDatabaseHelper = new MyDatabaseHelper_series(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        addSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSeries();
            }
        });

        Spinner spinner = findViewById(R.id.spinner_series);
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
                series_genre = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + book_genre,          Toast.LENGTH_LONG).show();
                //genre = movie_genre;
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Spinner spinner2 = findViewById(R.id.spinner4);
        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("Must watch!!");
        arrayList2.add("Interested");
        arrayList2.add("Okay");
        arrayList2.add("We'll see");

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                series_rating = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + book_genre,          Toast.LENGTH_LONG).show();
                //genre = movie_genre;
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }

    private void addSeries(){
        String movie = seriesName.getText().toString();
        String genre = series_genre;
        String suggested = suggestedBy.getText().toString();
        String rating = series_rating;

        long row_id = myDatabaseHelper.insertData(movie, genre, suggested, rating);
        if(row_id < 0){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
