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

    public List<Movie> getAllMovies() throws DatabaseException
    {
        try {
            return MovieEntity.toMovies(movieDao.queryForAll());
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
    }

    public int removeAll() throws DatabaseException
    {
        try {
            return movieDao.deleteBuilder().delete();
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
    }

    public Movie getMovie(String apiID) throws DatabaseException
    {
        try {
            return MovieEntity.toMovie((MovieEntity) movieDao.queryForEq("apiID", apiID));
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
    }

    public int addAllMovies(List<Movie> movies) throws DatabaseException
    {
        int count = 0;
        try {
            for (MovieEntity movie : MovieEntity.fromMovies(movies)) {
                count += movieDao.create(movie);
            }
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
        return count;
    }
}
