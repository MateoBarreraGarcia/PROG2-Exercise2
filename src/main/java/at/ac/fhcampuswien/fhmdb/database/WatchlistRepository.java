package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.WatchlistController;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WatchlistRepository {

    private Dao<WatchlistMovieEntity, Long> watchlistDao;

    /*public WatchlistRepository(ConnectionSource connectionSource) throws SQLException {
        movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
    }*/

    public WatchlistRepository()
    {
        this.watchlistDao = DatabaseManager.getInstance().getWatchlistDao();
    }

    public List<WatchlistMovieEntity> getWatchList() throws DatabaseException
    {
        try {
            return watchlistDao.queryForAll();
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
    }

    public List<Movie> getWatchListMovieList() throws DatabaseException
    {
        List<Movie> allMovies = new MovieRepository().getAllMovies();
        List<Movie> moviesOnWatchList = new ArrayList<>();
        try {
            WatchlistRepository watchlistRepository = new WatchlistRepository();

            for (WatchlistMovieEntity watchMovieEntry : watchlistRepository.getWatchList()) {
                moviesOnWatchList.addAll(allMovies.stream().filter(x -> x.getId() == watchMovieEntry.getApiID()).collect(Collectors.toList()));
            }
        } catch (DatabaseException dbe) {
            throw new DatabaseException(dbe.getMessage(), dbe.getCause());
        }
        return moviesOnWatchList;
    }

    public int addToWatchList(Movie movie) throws DatabaseException
    {
        WatchlistMovieEntity watchlistMovieEntity = new WatchlistMovieEntity(movie.getId());
        try {
            // TODO: For some reason the return 0 does not get trggered when the getApiID is the same
            for (WatchlistMovieEntity movieEntety : getWatchList()) {
                if (movieEntety.getApiID().equals(watchlistMovieEntity.getApiID())) return 0;
            }

            watchlistDao.create(watchlistMovieEntity);
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
        return 1;
    }

    public int removeFromWatchlist(String apiId) throws DatabaseException
    {
        try {
            for (WatchlistMovieEntity movieEntety : getWatchList()) {
                if (movieEntety.getApiID() == apiId) {
                    watchlistDao.delete(movieEntety);

                    //new WatchlistController().updateallMovieListWatchlist(getWatchListMovieList());   // TODO: Update the WatchList after a Movie has been removed
                    return 1;
                }
            }

            /*
            WatchlistMovieEntity foundMovie = (WatchlistMovieEntity) watchlistDao.queryForEq("apiID", apiId);
            if (foundMovie != null) {
                watchlistDao.delete(foundMovie);
                return 1;
            }
            */

        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
        return 0;
    }
}