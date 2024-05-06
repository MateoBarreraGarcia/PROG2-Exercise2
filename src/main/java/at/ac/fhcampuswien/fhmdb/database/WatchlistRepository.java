package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {

    private Dao<WatchlistMovieEntity, Long> watchlistDao;

    /*public WatchlistRepository(ConnectionSource connectionSource) throws SQLException {
        movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
    }*/

    public WatchlistRepository() {
        this.watchlistDao = DatabaseManager.getInstance().getWatchlistDao();
    }

    public List<WatchlistMovieEntity> getWatchList() throws SQLException {

        return watchlistDao.queryForAll();
    }

    public int addToWatchList(Movie movie) throws SQLException {
        WatchlistMovieEntity watchlistMovieEntity=new WatchlistMovieEntity(movie.getId());
        watchlistDao.create(watchlistMovieEntity);
        return 1;
    }

    public int removeFromWatchlist(String apiId) throws SQLException {
        WatchlistMovieEntity foundMovie = (WatchlistMovieEntity) watchlistDao.queryForEq("apiID", apiId);
        if (foundMovie != null) {
            watchlistDao.delete(foundMovie);
            return 1;
        }
        return 0;
    }
}