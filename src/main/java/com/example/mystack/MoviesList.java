package com.example.mystack;

import android.app.Activity;
import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mystack.Movie_basic;
import com.example.mystack.R;

import java.util.List;

public class MoviesList extends ArrayAdapter<Movie_basic> {
    private Activity context;
    private List<Movie_basic> moviesList;

    public MoviesList(@NonNull Activity context, List<Movie_basic> moviesList) {
        super(context, R.layout.list_layout_movie);
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_movie, null, true);

        TextView movie_name = listViewItem.findViewById(R.id.movie_name);
        TextView genre_name = listViewItem.findViewById(R.id.genre_name);
        TextView suggested_by = listViewItem.findViewById(R.id.suggested_by);
        TextView rating = listViewItem.findViewById(R.id.rating_movie);
        //ratingBar = listViewItem.findViewById(R.id.ratingBar);

        Movie_basic movie_basic = moviesList.get(position);

        movie_name.setText(movie_basic.getMovie_name());
        genre_name.setText(movie_basic.getMovie_genre());
        suggested_by.setText(movie_basic.getSuggested_by());
        rating.setText(movie_basic.getRating());

        return listViewItem;

    }
}
