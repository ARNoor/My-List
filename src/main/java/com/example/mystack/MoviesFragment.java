package com.example.mystack;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {
    View v;
    private RecyclerView myMovieList;
    private DatabaseReference databaseReference;
    private ListView listView;
    private MyDatabaseHelper databaseHelper;

    MyDatabaseHelper myDatabaseHelper;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.movies_layout, container, false);
        FloatingActionButton floatingActionButton = v.findViewById(R.id.fab_movie);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_movie_activity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton floatingActionButton2 = v.findViewById(R.id.fab_movie_delete);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Delete_movie_activity.class);
                startActivity(intent);
                //openDialogue();
            }
        });

        myDatabaseHelper = new MyDatabaseHelper(this.getContext());
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
/***/

        listView = v.findViewById(R.id.listViewMovieID);
        databaseHelper = new MyDatabaseHelper(this.getContext());
        loadData();
    }

    public void loadData(){
        ArrayList<String> listData = new ArrayList<>();
        final Cursor cursor = databaseHelper.showAllData();

        if(cursor.getCount() == 0){
            //Toast.makeText(context, "No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                listData.add(cursor.getString(0)+".\t"+cursor.getString(1)+"\t|\t"+cursor.getString(2)+"\n\t\t"+cursor.getString(3)+"\n\t\t"+cursor.getString(4));

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.list_layout, R.id.textViewName, listData);
        listView.setAdapter(adapter);

    }


}
