package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class MovieRepository {

    private Dao<MovieEntity, Integer> movieDao;

    public MovieRepository(ConnectionSource connectionSource) throws SQLException {
        movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
    }

    public List<MovieEntity> getAllMovies() throws SQLException {
        return movieDao.queryForAll();
    }

    public int removeAll() throws SQLException {
        return movieDao.deleteBuilder().delete();
    }

    public MovieEntity getMovie(int id) throws SQLException {
        return movieDao.queryForId(id);
    }

    public int addAllMovies(List<MovieEntity> movies) throws SQLException {
        int count = 0;
        for (MovieEntity movie : movies) {
            count += movieDao.create(movie);
        }
        return count;
    }
}
