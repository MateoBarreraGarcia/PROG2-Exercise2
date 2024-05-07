package at.ac.fhcampuswien.fhmdb.database;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

import java.util.ArrayList;
import java.util.List;


@DatabaseTable(tableName = "contact")
public class MovieEntity {

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField()
    private String apiID;
    @DatabaseField()
    private String title;
    @DatabaseField
    private String description;
    @DatabaseField()
    private String genres;
    @DatabaseField()
    private int releaseYear;
    @DatabaseField()
    private double rating;
    @DatabaseField()
    private String imgURL;
    @DatabaseField()
    private int lengthInMinutes;


    public MovieEntity(){}

    public MovieEntity(String apiID, String title, String description, String genres, int releaseYear, double rating, String imgURL, int lengthInMinutes) {
        this.apiID = apiID;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.imgURL = imgURL;
        this.lengthInMinutes = lengthInMinutes;
    }
    //to convert  list of genres to a string
    public static String genresToString (List<Genre> genres){
        String genreString="";
        for (Genre genre : genres){
            genreString=genreString+genre.toString();
            if (genres.indexOf(genre)!=genres.size()-1){
                genreString=genreString+",";
            }
        }
        return genreString;
    }
    //to convert list of movies to list of movie entities
    public static List<MovieEntity> fromMovies (List<Movie> movies){
        List<MovieEntity> movieEntities = new ArrayList<>();
        for (Movie movie : movies){
            movieEntities.add(fromMovie(movie));
        }

        return movieEntities;
    }
    //to convert list of movie entity to list of movie
    public static List <Movie> toMovies (List<MovieEntity> movieEntities){
        List<Movie> movies = new ArrayList<>();
        for (MovieEntity movieEntity : movieEntities) {
            movies.add(toMovie(movieEntity));
        }
        return movies;
    }
    public static MovieEntity fromMovie (Movie movie){

        return new MovieEntity(movie.getId(), movie.getTitle(), movie.getDescription(),genresToString(movie.getGenres()) , movie.getReleaseYear(), movie.getRating(), movie.getImgUrl(), movie.getLengthInMinutes());
    }
    public static Movie toMovie (MovieEntity movieEntity){

        return new Movie(movieEntity.title, movieEntity.description, genresToList(movieEntity.genres), movieEntity.apiID, movieEntity.releaseYear, movieEntity.imgURL, movieEntity.lengthInMinutes,movieEntity.rating);
    }
    //to convert string of genres to list of genres
    public static List<Genre> genresToList (String genres){
        List<Genre> genresList = new ArrayList<>();
        for (String s : genres.split(",")){
            genresList.add(Genre.valueOf(s));
        }
        return genresList;
    }



    /*public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public void setMainCast(List<String> mainCast) {
        this.mainCast = mainCast;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }*/
}
