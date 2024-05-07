package at.ac.fhcampuswien.fhmdb.database;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


import java.sql.SQLException;

public class DatabaseManager {
    public static final String URL = "jdbc:h2:file:./db/contactsdb";
    public static final String USER = "user";
    public static final String PASSWORD = "pass";

    private static ConnectionSource connectionSource;
    private Dao<MovieEntity, Long> movieDao;
    private Dao<WatchlistMovieEntity, Long> watchlistDao;

    private static DatabaseManager instance;

    private DatabaseManager() {
        try {
            createConnectionSource();
            initializeDao();
            createTables();
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    //to return the DAO for movies
    public Dao<MovieEntity, Long> getMovieDao() {
        return movieDao;
    }
    //to return the Dao for watchlist Movies
    public Dao<WatchlistMovieEntity, Long> getWatchlistDao() {
        return watchlistDao;
    }
    //to create the Database tables if they don`t exist
    private static void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
        TableUtils.createTableIfNotExists(connectionSource, WatchlistMovieEntity.class);
    }
    //to create the connection to the database
    private void createConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(URL, USER, PASSWORD);
    }
    //to initialize the DAOs
    private void initializeDao() throws SQLException {
        movieDao = DaoManager.createDao(connectionSource, MovieEntity.class);
        watchlistDao = DaoManager.createDao(connectionSource, WatchlistMovieEntity.class);
    }


}

