package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {

    private Dao<MovieEntity, Long> movieDao;

    public MovieRepository()
    {
        this.movieDao = DatabaseManager.getInstance().getMovieDao();
    }

    //to get all movies from the database
    public List<Movie> getAllMovies() throws DatabaseException
    {
        try {
            return MovieEntity.toMovies(movieDao.queryForAll());
        } catch (SQLException sqle) {
            //wraps SQLExceptionin a DatabsException
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
    }

    //to remove all movies from the database
    public int removeAll() throws DatabaseException
    {
        try {
            //to delete all records from the movie table
            return movieDao.deleteBuilder().delete();
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
    }

    //to get a movie by its apiID
    public Movie getMovie(String apiID) throws DatabaseException
    {
        try {
            //to query the database for a movie with the given apiID and convert to a Movie object
            return MovieEntity.toMovie((MovieEntity) movieDao.queryForEq("apiID", apiID));
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
    }

    //to add all movies to database
    public int addAllMovies(List<Movie> movies) throws DatabaseException
    {
        int count = 0;
        try {
            //loop through the list of movies, convert each to a MovieEntity, and create it in the database
            for (MovieEntity movie : MovieEntity.fromMovies(movies)) {
                count += movieDao.create(movie);
            }
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
        return count; //return e number of movies added
    }
}
