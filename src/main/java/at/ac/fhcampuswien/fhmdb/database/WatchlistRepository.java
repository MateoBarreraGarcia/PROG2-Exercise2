package at.ac.fhcampuswien.fhmdb.database;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {

    private Dao<MovieEntity, Integer> movieDao;

    public WatchlistRepository(ConnectionSource connectionSource) throws SQLException {
        movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
    }

    public List<MovieEntity> getWatchList() throws SQLException {

        return movieDao.queryForAll();
    }

    public int addToWatchList(MovieEntity movie) throws SQLException {

        return movieDao.create(movie);
    }

    public int removeFromWatchlist(String apiId) throws SQLException {
        return 0;
    }
}


