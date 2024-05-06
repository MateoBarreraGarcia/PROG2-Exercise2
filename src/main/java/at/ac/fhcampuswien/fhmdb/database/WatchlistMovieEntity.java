package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "Watchlist")
public class WatchlistMovieEntity extends MovieEntity {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String apiID;

    public WatchlistMovieEntity() {
    }

    public WatchlistMovieEntity(String apiID) {
        this.apiID = apiID;

    }

    /*public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter und Setter für apiID
    public String getApiID() {
        return apiID;
    }

    public void setApiID(String apiID) {
        this.apiID = apiID;
    }

    // Getter und Setter für title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter und Setter für description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter und Setter für genres
    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    // Getter und Setter für releaseYear
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    // Getter und Setter für rating
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }*/

}

