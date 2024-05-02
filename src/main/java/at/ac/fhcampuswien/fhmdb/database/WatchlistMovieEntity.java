package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Watchlist")
public class WatchlistMovieEntity extends MovieEntity {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String apiID;

    @DatabaseField
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private String genres;

    @DatabaseField
    private int releaseYear;

    @DatabaseField
    private double rating;

    public WatchlistMovieEntity() {
    }

    public WatchlistMovieEntity(String apiID, String title, String description, String genres, int releaseYear, double rating) {
        this.apiID = apiID;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    // Getters and setters
}

