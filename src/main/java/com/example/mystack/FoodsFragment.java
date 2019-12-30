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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FoodsFragment extends Fragment {
    View v;
    private ListView listView;
    private MyDatabaseHelper_foods databaseHelper;

    MyDatabaseHelper myDatabaseHelper;
    Context context;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.foods_layout, container, false);
        FloatingActionButton floatingActionButton = v.findViewById(R.id.fab_food);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Add_foods_activity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton floatingActionButton2 = v.findViewById(R.id.fab_food_delete);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Delete_food_activity.class);
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
        listView = v.findViewById(R.id.list_view_foods_ID);
        databaseHelper = new MyDatabaseHelper_foods(this.getContext());
        LoadData();
    }

    public void LoadData(){
        ArrayList<String> listData = new ArrayList<>();
        final Cursor cursor2 = databaseHelper.showAllData();

        if(cursor2.getCount() == 0){
           // Toast.makeText(context, "No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor2.moveToNext()){
                listData.add(cursor2.getString(0)+".\t"+cursor2.getString(1)+"\t|\t"+cursor2.getString(2)+"\n\t\t"+cursor2.getString(3)+"\n\t\t"+cursor2.getString(4)+"\n\t\t"+cursor2.getString(5));

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.list_layout, R.id.textViewName, listData);
        listView.setAdapter(adapter);

    }
}
