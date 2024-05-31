package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.Interfaces.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.builders.MovieAPIRequestBuilder;
import at.ac.fhcampuswien.fhmdb.database.MovieRepository;
import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.exceptions.MovieApiException;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.Screen;
import at.ac.fhcampuswien.fhmdb.states.NoSortingState;
import at.ac.fhcampuswien.fhmdb.states.State;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
//import at.ac.fhcampuswien.fhmdb.database.WatchlistRepository;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import java.stream.Collectors;

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

    protected State sortingState;

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

    private String yearFilerNoFilter = "No Year filter";
    private String ratingFilerNoFilter = "No Rating filter";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        initializeState();
        initializeLayout();
    }

    public void initializeState()
    {
        String url = new MovieAPIRequestBuilder(MovieAPIRequestBuilder.ALL_MOVIES_PATH).build();
        searchAPIForMovies(url);

        changeState(new NoSortingState(this));
        sortingState.sortMovies();
    }

    public void initializeLayout()
    {
        movieListView.setItems(observableMovies);   // set the items of the listview to the observable list
            movieListView.setCellFactory(movieListView -> {
                try {
                    return new MovieCell(Screen.HOME, onAddToWatchlistClicked); // apply custom cells to the listview
                } catch (DatabaseException dbe) {
                    printErrorMassage(dbe.getMessage());
                    return null;
                }
            });

        Object[] genres = Genre.values();   // get all genres
        //genreComboBox.getItems().add("No Genre filter");  // add "no filter" to the combobox
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(genres);    // add all genres to the combobox


        yearComboBox.setEditable(true);
        yearComboBox.setPromptText("Filter for Year");
        yearComboBox.getItems().add(yearFilerNoFilter);
        yearComboBox.getItems().addAll(observableMovies.stream().map(o -> o.getReleaseYear()).distinct().sorted().collect(Collectors.toList()));


        // Ein Slider bei welchen man ein minimum und Maximum einstellen kann währe besser als ein dropdown
        // Von bis eingabe Felder oder Auf- / Absteigend nach Rating sortieren
        ratingComboBox.setEditable(true);
        ratingComboBox.setPromptText("Filter for Rating");
        ratingComboBox.getItems().add(ratingFilerNoFilter);


        ratingComboBox.getItems().addAll(ratingSelectionList);


    }

    public ObservableList<Movie> getObservableMovies() {
        return observableMovies;
    }

    public State getSortingState() {
        return sortingState;
    }

    public void changeState(State state) {
        this.sortingState = state;
    }

    /* These methods are no longer needed as the sorting functionality is now provided by the State classes
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
     */

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

    public void searchAPIForMovies(String url) {
        MovieAPI movieAPI = new MovieAPI();

        try {
            List<Movie> movieList = movieAPI.getMoviesRequest(url);
            updateAllMoviesList(movieList);

            // Fill cache only when all Movies are requested
            if(url.equals(MovieAPIRequestBuilder.ALL_MOVIES_PATH)) {
                MovieRepository movieRepository = new MovieRepository();
                movieRepository.removeAll();
                movieRepository.addAllMovies(movieList);
            }
        } catch (MovieApiException e) {
            // API call failed, try to load movies from the database
            try {
                List<Movie> movieList = new MovieRepository().getAllMovies();

                // Filter when API does not work
                /* 30.5.: commented out for builder refactoring with builder pattern
                if(searchQuery != null) movieList.stream().filter(x -> x.getTitle().contains(searchQuery) || x.getDescription().contains(searchQuery));
                if(genre != null) movieList.stream().filter(x -> x.getGenres().contains(genre));
                if(year != null) movieList.stream().filter(x -> x.getReleaseYear() == year);
                if(rating != null) movieList.stream().filter(x -> x.getRating() == rating);
                */

                updateAllMoviesList(movieList);
            }catch (DatabaseException dde){
                // API and SQL Server failed
                printErrorMassage(dde.getMessage());
            }
        }catch (DatabaseException dde){
            // Cache could not be filled
            printErrorMassage(dde.getMessage());
        }
    }

    public void printErrorMassage(String errorMassage){
        observableMovies.clear();
        Label message = new Label("Oopsie Woopsie! It seems there was a problem: " + errorMassage);
        message.getStyleClass().add("error-screen");
        movieListView.setPlaceholder(message);
    }

    private void updateAllMoviesList(List<Movie> movies){
        allMovies = movies;

        // update the ui with the list returned from the request
        if (allMovies.isEmpty()) {
            Label label = new Label("No results found");
            label.getStyleClass().add("error-screen");
            movieListView.setPlaceholder(label);
            observableMovies.clear(); // Clear any existing movie data
        } else {
            observableMovies.clear();
            observableMovies.addAll(allMovies);
        }
    }

    public void searchBtnClicked(ActionEvent actionEvent)
    {
        yearComboBox.getStyleClass().removeIf(style -> style.equals("-fx-text-box-border: red;"));
        ratingComboBox.getStyleClass().removeIf(style -> style.equals("-fx-text-box-border: red;"));

        String searchQuery = searchField.getText().trim().toLowerCase();
        Object genre = genreComboBox.getSelectionModel().getSelectedItem();
        Integer year = null;
        try {
            String r = yearComboBox.getEditor().getText();
            if (!yearComboBox.getEditor().getText().equals("") && !yearComboBox.getEditor().getText().equals(yearFilerNoFilter))
                year = Integer.parseInt(yearComboBox.getEditor().getText());
        } catch (NumberFormatException e) {
            yearComboBox.setStyle("-fx-text-box-border: red;");
            return;
        }

        Double rating = null; // Default value can be set because if nothing is set it is like 0.0 an above
        try {
            String r = ratingComboBox.getEditor().getText();
            if (!ratingComboBox.getEditor().getText().equals("") && !ratingComboBox.getEditor().getText().equals(ratingFilerNoFilter))
                rating = Double.parseDouble(ratingComboBox.getEditor().getText().replace("+",""));
        } catch (NumberFormatException e) {
            ratingComboBox.setStyle("-fx-text-box-border: red;");
            return;
        }

        String url = new MovieAPIRequestBuilder(MovieAPIRequestBuilder.ALL_MOVIES_PATH)
                .query(searchQuery)
                .genre(genre)
                .releaseYear(year)
                .ratingFrom(rating)
                .build();

        searchAPIForMovies(url);
        sortingState.sortMovies(); // this keeps the current sorting state after an api request
    }

    public void sortBtnClicked(ActionEvent actionEvent)
    {
        sortingState.onSortBtnClicked();
    }

    //Streams:
    public int getLongestMovieTitle(List<Movie> movies)
    {
        return movies.stream()
                .mapToInt(movie -> movie.getTitle().length())
                .max()
                .orElse(0);
    }

    public long countMoviesFrom(List<Movie> movies, String director)
    {
        return movies.stream()
                .filter(movie -> movie.getDirectors().contains(director))
                .count();
    }


    public List<Movie> getMoviesBetweenYears(List<Movie> movies, int startYear, int endYear)
    {
        if (startYear > endYear) {
            throw new IllegalArgumentException("Start year cannot be greater than end year");
        }

        List<Movie> filteredMovies = movies.stream()
                .filter(Objects::nonNull)
                .filter(m -> m.getReleaseYear() >= startYear && m.getReleaseYear() <= endYear)
                .toList();

        return filteredMovies;
    }

    public String getMostPopularActor(List<Movie> movies)
    {
        return movies.stream().map(Movie::getMainCast) // Gets the mainCast Array
                .flatMap(o -> Arrays.stream(o.toArray())) // creates a sequential Stream of the arrays
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // collects and groups the occurrences of the strings
                .entrySet().stream()    // Uses a stream on the hashmap
                .max(Map.Entry.comparingByValue())  // Gets the element with the highest number
                .map(Map.Entry::getKey).orElse("").toString();  // Returns the key of the element with the highest number
    }

    private final ClickEventHandler onAddToWatchlistClicked = (clickedItem) -> {
        WatchlistRepository watchlistRepository = new WatchlistRepository();
        try {
            Movie movie = (Movie) clickedItem;
            watchlistRepository.addToWatchList(movie);
        }catch(ClassCastException cce){
            throw new DatabaseException(cce.getMessage(), cce.getCause());
        }
    };
}