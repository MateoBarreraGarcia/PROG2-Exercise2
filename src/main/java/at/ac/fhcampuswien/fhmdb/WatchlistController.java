package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.Screen;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.util.Arrays;
import java.util.List;

public class WatchlistController {
    @FXML
    public JFXListView movieListView;

    protected ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

    public void initialize() {
        initializeState();
        initializeLayout();
    }

    public void initializeState() {
        observableMovies.addAll(getDummyWatchlistData());
    }

    public void initializeLayout() {
        movieListView.setItems(observableMovies);
        movieListView.setCellFactory(movieListView -> new MovieCell(Screen.WATCHLIST));
    }

    private List<Movie> getDummyWatchlistData() {
        return Arrays.asList(
                new Movie("Interstellar",
                        "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
                        Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION), "8382349", 2014, "jhecbsdhc", 169,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nola"),
                        Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"), 8.7),
                new Movie("The Shawshank Redemption",
                        "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.",
                        Arrays.asList(Genre.DRAMA), "8382349", 1994, "jhecbsdhc", 144,
                        Arrays.asList("Frank Darabont"), Arrays.asList("Stephen King", "Frank Darabont"),
                        Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton"), 9.3),
                new Movie("Inception",
                        "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                        Arrays.asList(Genre.DRAMA), "8382349", 2010, "jhecbsdhc", 148,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nolan"),
                        Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"), 8.8),
                new Movie("The Godfather",
                        "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                        Arrays.asList(Genre.DRAMA), "", 1972, "", 175,
                        Arrays.asList("Francis Ford Coppola"), Arrays.asList("Mario Puzo", "Francis Ford Coppola"),
                        Arrays.asList("Marlon Brando", "Al Pacino", "James Caan"), 9.2),
                new Movie("The Lion King",
                        "Lion cub and future king Simba searches for his identity. His eagerness to please others and penchant for testing his boundaries sometimes gets him into trouble.",
                        Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.DRAMA, Genre.FAMILY, Genre.MUSICAL), "", 1994, "", 88,
                        Arrays.asList("Roger Allers", "Rob Minkoff"), Arrays.asList("Irene Mecchi", "Jonathan Roberts", "Linda Woolverton"),
                        Arrays.asList("Matthew Broderick", "Jeremy Irons", "James Earl Jones"), 8.5));
    }
}
