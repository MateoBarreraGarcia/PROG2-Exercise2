package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.Interfaces.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.WatchlistController;
import at.ac.fhcampuswien.fhmdb.exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.Screen;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.stream.Collectors;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genre = new Label();
    private final Label releaseYear = new Label();
    private final Label rating = new Label();
    private final HBox additionalInfos = new HBox(releaseYear, rating);
    private final Button watchlistBtn = new Button();
    private final VBox textLayout = new VBox(title, detail, genre, additionalInfos);
    private final Region layoutFill = new Region();
    private final HBox mainLayout = new HBox(textLayout, layoutFill, watchlistBtn);

    public MovieCell(Screen screen, ClickEventHandler watchlistClicked) throws DatabaseException {
        if (screen == Screen.HOME) watchlistBtn.setText("Add to Watchlist");
        else if (screen == Screen.WATCHLIST) watchlistBtn.setText("Remove");

        watchlistBtn.setOnMouseClicked(mouseEvent -> {
            try {
                watchlistClicked.onClick(getItem());
            } catch (DatabaseException dbe) {
                new HomeController().printErrorMassage(dbe.getMessage());
            }
        });
    }

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setGraphic(null);
            setText(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );

            String genres = movie.getGenres()
                    .stream()
                    .map(Enum::toString)
                    .collect(Collectors.joining(", "));
            genre.setText(genres);
            releaseYear.setText("Release Year:  " + movie.getReleaseYear());
            rating.setText("Rating:  " + movie.getRating());


            // color scheme
            title.getStyleClass().add("text-blue");
            detail.getStyleClass().add("text-white");
            genre.getStyleClass().add("text-white");
            genre.setStyle("-fx-font-style: italic");
            releaseYear.getStyleClass().add("text-white");
            rating.getStyleClass().add("text-white");

            // layout
            title.fontProperty().set(title.getFont().font(20));
            detail.setMaxWidth(this.getScene().getWidth() - 30);
            detail.setWrapText(true);
            releaseYear.setPadding(new Insets(0, 15, 0, 0));
            textLayout.spacingProperty().set(10);
            textLayout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);

            // Exercise 3: new Button to add or remove movies from watchlist
            watchlistBtn.getStyleClass().add("background-blue");

            HBox.setHgrow(layoutFill, Priority.ALWAYS); // this makes the watchlistBtn stick to the right end of the screen
            mainLayout.setPadding(new Insets(10));
            mainLayout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));
            setGraphic(mainLayout);
        }
    }
}

