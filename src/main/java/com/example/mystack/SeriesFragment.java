package com.example.mystack;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class SeriesFragment extends Fragment {
    View v;
    private ListView listView3;
    private MyDatabaseHelper_series databaseHelper;

    MyDatabaseHelper_series myDatabaseHelper;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.series_layout, container, false);
        //inflater.inflate(R.layout.movies_layout, container, false);
        FloatingActionButton floatingActionButton = v.findViewById(R.id.fab_seires);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_series_activity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton floatingActionButton2 = v.findViewById(R.id.fab_series_delete);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Delete_series_activity.class);
                startActivity(intent);
                //openDialogue();
            }
        });

        myDatabaseHelper = new MyDatabaseHelper_series(this.getContext());
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        listView3 = v.findViewById(R.id.list_view_series_ID);
        databaseHelper = new MyDatabaseHelper_series(this.getContext());
        //loadData();
        ArrayList<String> listData = new ArrayList<>();
        final Cursor cursor3 = databaseHelper.showAllData();

        if(cursor3.getCount() == 0){
            //Toast.makeText(context, "No data to show", Toast.LENGTH_SHORT).show();
            //Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor3.moveToNext()){
                listData.add(cursor3.getString(0)+".\t"+cursor3.getString(1)+"\t|\t"+cursor3.getString(2)+"\n\t\t"+cursor3.getString(3)+"\n\t\t"+cursor3.getString(4));

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.list_layout, R.id.textViewName, listData);
        listView3.setAdapter(adapter);
    }

}
