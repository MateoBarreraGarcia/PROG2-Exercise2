package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortedState;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
//import java.stream.Collectors;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;
    @FXML
    public JFXComboBox yearComboBox;
    @FXML
    public JFXComboBox ratingComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies;

    protected ObservableList<Movie> observableMovies = FXCollections.observableArrayList();

    protected SortedState sortedState;

    private ArrayList<String> ratingSelectionList = new ArrayList<>() {{
        add("9.0+");
        add("8.0+");
        add("7.0+");
        add("6.0+");
        add("5.0+");
        add("4.0+");
        add("3.0+");
        add("2.0+");
        add("1.0+");
        add("0.0+");
    }};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        initializeState();
        initializeLayout();
    }

    public void initializeState()
    {
        allMovies = Movie.initializeMovies();
        observableMovies.clear();
        observableMovies.addAll(allMovies); // add all movies to the observable list
        sortedState = SortedState.NONE;
    }

    public void initializeLayout()
    {
        movieListView.setItems(observableMovies);   // set the items of the listview to the observable list
        movieListView.setCellFactory(movieListView -> new MovieCell()); // apply custom cells to the listview

        Object[] genres = Genre.values();   // get all genres
        genreComboBox.getItems().add("No Genre filter");  // add "no filter" to the combobox
        genreComboBox.getItems().addAll(genres);    // add all genres to the combobox
        genreComboBox.setPromptText("Filter by Genre");


        yearComboBox.setEditable(true);
        yearComboBox.setPromptText("Filter for Year");
        yearComboBox.getItems().add("No Year filter");
        yearComboBox.getItems().addAll(observableMovies.stream().map(o -> o.getReleaseYear()).distinct().collect(Collectors.toList()));


        // Ein Slider bei welchen man ein minimum und Maximum einstellen kann währe besser als ein dropdown
        // Von bis eingabe Felder oder Auf- / Absteigend nach Rating sortieren
        ratingComboBox.setEditable(true);
        ratingComboBox.setPromptText("Filter for Rating");
        ratingComboBox.getItems().add("No Rating filter");


        ratingComboBox.getItems().addAll(ratingSelectionList);


    }

    public void sortMovies()
    {
        if (sortedState == SortedState.NONE || sortedState == SortedState.DESCENDING) {
            sortMovies(SortedState.ASCENDING);
        } else if (sortedState == SortedState.ASCENDING) {
            sortMovies(SortedState.DESCENDING);
        }
    }

    // sort movies based on sortedState
    // by default sorted state is NONE
    // afterwards it switches between ascending and descending
    public void sortMovies(SortedState sortDirection)
    {
        if (sortDirection == SortedState.ASCENDING) {
            observableMovies.sort(Comparator.comparing(Movie::getTitle));
            sortedState = SortedState.ASCENDING;
        } else {
            observableMovies.sort(Comparator.comparing(Movie::getTitle).reversed());
            sortedState = SortedState.DESCENDING;
        }
    }

    public List<Movie> filterByQuery(List<Movie> movies, String query)
    {
        if (query == null || query.isEmpty()) return movies;

        if (movies == null) {
            throw new IllegalArgumentException("movies must not be null");
        }

        return movies.stream()
                .filter(Objects::nonNull)
                .filter(movie ->
                        movie.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                                movie.getDescription().toLowerCase().contains(query.toLowerCase())
                )
                .toList();
    }

    public List<Movie> filterByGenre(List<Movie> movies, Genre genre)
    {
        if (genre == null) return movies;

        if (movies == null) {
            throw new IllegalArgumentException("movies must not be null");
        }

        return movies.stream()
                .filter(Objects::nonNull)
                .filter(movie -> movie.getGenres().contains(genre))
                .toList();
    }

    public void applyAllFilters(String searchQuery, Object genre)
    {
        List<Movie> filteredMovies = allMovies;

        if (!searchQuery.isEmpty()) {
            filteredMovies = filterByQuery(filteredMovies, searchQuery);
        }

        if (genre != null && !genre.toString().equals("No filter")) {
            filteredMovies = filterByGenre(filteredMovies, Genre.valueOf(genre.toString()));
        }

        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
    }

    public void searchAPIForMovies(String searchQuery, Object genre, int year, double rating) {

        MovieAPI movieAPI = new MovieAPI();

        String url = movieAPI.generateRequestString(searchQuery, genre, year, rating);

        try {

            List<Movie> filteredMovies = movieAPI.getRequest(url);
            // update the ui with the list returned from the request
            observableMovies.clear();
            observableMovies.addAll(filteredMovies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchBtnClicked(ActionEvent actionEvent)
    {
        yearComboBox.getStyleClass().removeIf(style -> style.equals("-fx-text-box-border: red;"));
        ratingComboBox.getStyleClass().removeIf(style -> style.equals("-fx-text-box-border: red;"));

        String searchQuery = searchField.getText().trim().toLowerCase();
        Object genre = genreComboBox.getSelectionModel().getSelectedItem();
        int year;
        try{
            year = Integer.parseInt(yearComboBox.getEditor().getText());
        }catch(NumberFormatException e){
            yearComboBox.setStyle("-fx-text-box-border: red;");
            return;
        }

        double rating = 0.0; // Default value can be set because if nothing is set it is like 0.0 an above
        try{
            rating = Double.parseDouble(ratingComboBox.getEditor().getText());
        }catch(NumberFormatException e){
            yearComboBox.setStyle("-fx-text-box-border: red;");
            return;
        }

        searchAPIForMovies(searchQuery, genre, year, rating);
        sortMovies(sortedState);
    }

    public void sortBtnClicked(ActionEvent actionEvent)
    {
        sortMovies();
    }

    //Streams:
    public int getLongestMovieTitle (List<Movie> movies){
        return movies.stream()
                .mapToInt(movie ->movie.getTitle().length())
                .max()
                .orElse(0);
    }

    public long countMoviesFrom(List<Movie> movies, String director){
        return movies.stream()
                .filter(movie -> movie.getDirectors().equals(director))
                .count();
    }


    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear) {
        List<Movie> filteredMovies = movies.stream()
                .filter(Objects::nonNull)
                .filter(m -> m.getReleaseYear() >= startYear && m.getReleaseYear() <= endYear)
                .toList();

        return filteredMovies;
    }

    public String getMostPopularActor(List<Movie> movies){
        var a = movies.stream().collect(Collectors.groupingBy(o -> o.getMainCast(), Collectors.counting()));

        var c = movies.stream().filter(f -> f != null).collect(Collectors.groupingBy(o -> o.getMainCast(), Collectors.counting()));

        //var b = movies.stream().collect(Collectors.groupingBy(Movie::getMainCast, Collectors.collectingAndThen()));

        return "Hi";
    }
}