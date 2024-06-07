package at.ac.fhcampuswien.fhmdb.database;

import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.Interfaces.ObserverWatchListMovies;
import at.ac.fhcampuswien.fhmdb.Interfaces.SubjectWatchListMovies;
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

public class WatchlistRepository implements SubjectWatchListMovies {

    private static WatchlistRepository instance;
    private Dao<WatchlistMovieEntity, Long> watchlistDao;

    private List<ObserverWatchListMovies> observerWatchListMovies = new ArrayList<>();
    /*public WatchlistRepository(ConnectionSource connectionSource) throws SQLException {
        movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
    }*/

    private WatchlistRepository()
    {
        this.watchlistDao = DatabaseManager.getInstance().getWatchlistDao();
    }

    public static WatchlistRepository getInstance() {
        if (instance == null) {
            instance = new WatchlistRepository();
        }
        return instance;
    }

    //to get all movies in watchlist
    public List<WatchlistMovieEntity> getWatchList() throws DatabaseException
    {
        try {
            return watchlistDao.queryForAll();
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }
    }

    //to get the list of movies on the watchlist
    public List<Movie> getWatchListMovieList() throws DatabaseException
    {
        //get all movies from database
        List<Movie> allMovies = MovieRepository.getInstance().getAllMovies();
        List<Movie> moviesOnWatchList = new ArrayList<>();
        try {
            WatchlistRepository watchlistRepository = WatchlistRepository.getInstance();

            //iterate through each movie entry in the watchlist
            for (WatchlistMovieEntity watchMovieEntry : watchlistRepository.getWatchList()) {

                //filter and collect movies on the watchlist from all movies
                moviesOnWatchList.addAll(allMovies.stream().filter(x -> x.getId() == watchMovieEntry.getApiID()).collect(Collectors.toList()));
            }
        } catch (DatabaseException dbe) {
            throw new DatabaseException(dbe.getMessage(), dbe.getCause());
        }
        //return the list of movies on the watchlist
        return moviesOnWatchList;
    }

    //to add a movie to the watchlist
    public int addToWatchList(Movie movie) throws DatabaseException
    {
        WatchlistMovieEntity watchlistMovieEntity = new WatchlistMovieEntity(movie.getId());
        try { //check if the movie already exists in the watchlist
            // TODO: For some reason the return 0 does not get trggered when the getApiID is the same
            for (WatchlistMovieEntity movieEntety : getWatchList()) {
                if (movieEntety.getApiID().equals(watchlistMovieEntity.getApiID())) {
                    notifyObserver(movie, false);
                    return 0;
                }
            }
            //create a new entry in the watchlist for the movie
            watchlistDao.create(watchlistMovieEntity);
        } catch (SQLException sqle) {
            throw new DatabaseException(sqle.getMessage(), sqle.getCause());
        }

        notifyObserver(movie, true);

        //return 1 to indicate successful addition
        return 1;
    }

    public int removeFromWatchlist(String apiId) throws DatabaseException
    {
        try {
            // iterate through each movie entry in the watchlist
            for (WatchlistMovieEntity movieEntety : getWatchList()) {
                if (movieEntety.getApiID() == apiId) {

                    //delete the movie entry from the watchlist
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
        //to return 0 if movie was not found in the watchlist
        return 0;
    }

    @Override
    public void addObserver(ObserverWatchListMovies observerWatchListMovies)
    {
        this.observerWatchListMovies.add(observerWatchListMovies);
    }

    @Override
    public void removeObserver(ObserverWatchListMovies observerWatchListMovies)
    {
        this.observerWatchListMovies.remove(observerWatchListMovies);
    }

    @Override
    public void notifyObserver(Movie movie, boolean success)
    {
        for (ObserverWatchListMovies observerWatchListMovies : this.observerWatchListMovies){
            observerWatchListMovies.update(movie, success);
        }
    }
}