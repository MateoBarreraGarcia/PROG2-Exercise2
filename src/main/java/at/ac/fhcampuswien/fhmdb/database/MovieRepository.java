package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {

    private Dao<MovieEntity, Long> movieDao;

    public MovieRepository() {
        this.movieDao = DatabaseManager.getInstance().getMovieDao();
    }

    public List<Movie> getAllMovies() throws SQLException {
        return MovieEntity.toMovies(movieDao.queryForAll());
    }

    public int removeAll() throws SQLException {
        return movieDao.deleteBuilder().delete();
    }

    public Movie getMovie(String apiID) throws SQLException {
        return MovieEntity.toMovie((MovieEntity) movieDao.queryForEq("apiID", apiID));
    }

    public int addAllMovies(List<Movie> movies) throws SQLException {
        int count = 0;
        for (MovieEntity movie : MovieEntity.fromMovies(movies)) {
            count += movieDao.create(movie);
        }
        return count;
    }
}
