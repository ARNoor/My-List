package com.example.mystack;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.example.mystack.R.layout.list_layout;

public class ArtistList extends ArrayAdapter<Movie_basic> {
    private Activity context;
    private List<Movie_basic> artistList;

    public ArtistList(Activity context, List<Movie_basic> artistList) {
        super(context, list_layout, artistList);
        this.context = context;
        this.artistList = artistList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        ///TextView textViewGenre = listViewItem.findViewById(R.id.textViewGenre);
        //TextView suggested_by = listViewItem.findViewById(R.id.suggested_by);
        //TextView rating = listViewItem.findViewById(R.id.rating_movie);
        //ratingBar = listViewItem.findViewById(R.id.ratingBar);

        Movie_basic movie_basic = artistList.get(position);

        textViewName.setText(movie_basic.getMovie_name());
        ///textViewGenre.setText(movie_basic.getMovie_genre());
        //suggested_by.setText(movie_basic.getSuggested_by());
        //rating.setText(movie_basic.getRating());

        return listViewItem;
    }


}
