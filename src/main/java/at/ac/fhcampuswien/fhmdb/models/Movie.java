package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.MovieAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private final String title;
    private final String description;
    private final List<Genre> genres;
    private final String id;
    private final int releaseYear;
    private String imgUrl;
    private int lengthInMinutes;
    private List<String> directors;
    private List <String> writers;
    private List <String>  mainCast;
    private double rating;

    public Movie(String title, String description, List<Genre> genres, String id, int releaseYear, String imgUrl, int lengthInMinutes, List<String> directors, List<String> writers, List<String> mainCast, double rating)
    {
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.id = id;
        this.releaseYear = releaseYear;
        this.imgUrl = imgUrl;
        this.lengthInMinutes = lengthInMinutes;
        this.directors = directors;
        this.writers = writers;
        this.mainCast = mainCast;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Movie other)) {
            return false;
        }
        return this.title.equals(other.title) && this.description.equals(other.description) && this.genres.equals(other.genres);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getId()
    {
        return id;
    }

    public int getReleaseYear()
    {
        return releaseYear;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public int getLengthInMinutes()
    {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes)
    {
        this.lengthInMinutes = lengthInMinutes;
    }

    public List<String> getDirectors()
    {
        return directors;
    }

    public void setDirectors(List<String> directors)
    {
        this.directors = directors;
    }

    public List<String> getWriters()
    {
        return writers;
    }

    public void setWriters(List<String> writers)
    {
        this.writers = writers;
    }

    public List<String> getMainCast()
    {
        return mainCast;
    }

    public void setMainCast(List<String> mainCast)
    {
        this.mainCast = mainCast;
    }

    public double getRating()
    {
        return rating;
    }

    public void setRating(double rating)
    {
        this.rating = rating;
    }

    public static List<Movie> initializeMovies(){
        MovieAPI movieAPI = new MovieAPI();
        try {
            return movieAPI.getRequest(movieAPI.generateRequestString());
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }
}
