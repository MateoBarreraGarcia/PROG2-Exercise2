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

    private ConnectionSource connectionSource;
    protected Dao<MovieEntity, Integer> dao;

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


    public Dao<MovieEntity, Integer> getDao() {
        return dao;
    }

    private void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, MovieEntity.class);
    }

    private void createConnectionSource() throws SQLException {
        connectionSource = new JdbcConnectionSource(URL, USER, PASSWORD);
    }

    private void initializeDao() throws SQLException {
        dao = DaoManager.createDao(connectionSource, MovieEntity.class);
    }
}

