package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Screen;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    BorderPane mainPane;

    @FXML
    Button homeBtn;

    @FXML
    Button watchlistBtn;

    @FXML
    Button aboutBtn;

    public void initialize() {
        System.out.println("Main view started");
        loadHomeView();
    }

    public void setContentView(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        try {
            mainPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("An error occured while loading fxml: " + fxml);
        }
    }

    public void loadHomeView() {
        setContentView(Screen.HOME.path);
        watchlistBtn.getStyleClass().remove("nav-btn-selected");
        aboutBtn.getStyleClass().remove("nav-btn-selected");
        homeBtn.getStyleClass().add("nav-btn-selected");
    }

    public void loadWatchlistView() {
        setContentView(Screen.WATCHLIST.path);
        homeBtn.getStyleClass().remove("nav-btn-selected");
        aboutBtn.getStyleClass().remove("nav-btn-selected");
        watchlistBtn.getStyleClass().add("nav-btn-selected");
    }

    public void loadAboutView() {
        setContentView(Screen.ABOUT.path);
        homeBtn.getStyleClass().remove("nav-btn-selected");
        watchlistBtn.getStyleClass().remove("nav-btn-selected");
        aboutBtn.getStyleClass().add("nav-btn-selected");
    }
}
