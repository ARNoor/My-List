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

public class Add_foods_activity extends AppCompatActivity {
    EditText foodName;
    EditText suggestedBy;
    EditText restaurant;
    EditText location;
    Button addFood;

    String food_rating;

    MyDatabaseHelper_foods myDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_foods);

        foodName = findViewById(R.id.food_name_ID);
        restaurant = findViewById(R.id.restaurant_ID);
        location = findViewById(R.id.located_ID);
        suggestedBy = findViewById(R.id.suggested_by_food_ID);
        addFood = findViewById(R.id.add_food_B);
        myDatabaseHelper = new MyDatabaseHelper_foods(this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addFood();
                String movie = foodName.getText().toString();
                String genre = restaurant.getText().toString();
                String genre2 = location.getText().toString();
                String suggested = suggestedBy.getText().toString();
                String rating = food_rating;

                long row_id = myDatabaseHelper.insertData(movie, genre, genre2, suggested, rating);
                if(row_id < 0){
                    //Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Add_foods_activity.this, "Error", Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(this, "Successfully added row "+row_id, Toast.LENGTH_SHORT).show();
                    Toast.makeText(Add_foods_activity.this, "Successfully added", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


        Spinner spinner2 = findViewById(R.id.spinner3);
        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("Must eat!!");
        arrayList2.add("Interested");
        arrayList2.add("Okay");
        arrayList2.add("We'll see");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList2);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 food_rating= parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + book_genre,          Toast.LENGTH_LONG).show();
                //genre = movie_genre;
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }

}
