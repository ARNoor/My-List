package com.example.mystack;

public class Movie_basic {
    String movieID;
    String movie_name;
    String movie_genre;
    String suggested_by;
    String rating;

    public Movie_basic() {
    }

    public Movie_basic(String movieID, String movie_name, String movie_genre, String suggested_by, String rating) {
        this.movieID = movieID;
        this.movie_name = movie_name;
        this.movie_genre = movie_genre;
        this.suggested_by = suggested_by;
        this.rating = rating;
    }

    public String getMovieID() {
        return movieID;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public String getSuggested_by() {
        return suggested_by;
    }

    public String getRating() {
        return rating;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public void setMovie_genre(String movie_genre) {
        this.movie_genre = movie_genre;
    }

    public void setSuggested_by(String suggested_by) {
        this.suggested_by = suggested_by;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
