package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "Watchlist")
public class WatchlistMovieEntity {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private String apiID;

    public WatchlistMovieEntity() {
    }

    public WatchlistMovieEntity(String apiID) {
        this.apiID = apiID;

    }

    public String getApiID()
    {
        return apiID;
    }
}

