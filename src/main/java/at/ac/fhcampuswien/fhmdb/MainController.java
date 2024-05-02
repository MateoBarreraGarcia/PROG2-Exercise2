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
        loadHomeView();
    }

    public boolean setContentView(String fxml){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        try {
            mainPane.setCenter(fxmlLoader.load());
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while loading fxml: " + fxml);
        }
        return false;
    }

    public void loadHomeView() {
        if (!setContentView(Screen.HOME.path)) return;
        watchlistBtn.getStyleClass().remove("nav-btn-selected");
        aboutBtn.getStyleClass().remove("nav-btn-selected");
        homeBtn.getStyleClass().add("nav-btn-selected");
    }

    public void loadWatchlistView() {
        if (!setContentView(Screen.WATCHLIST.path)) return;
        homeBtn.getStyleClass().remove("nav-btn-selected");
        aboutBtn.getStyleClass().remove("nav-btn-selected");
        watchlistBtn.getStyleClass().add("nav-btn-selected");
    }

    public void loadAboutView() {
        if (!setContentView(Screen.ABOUT.path)) return;
        homeBtn.getStyleClass().remove("nav-btn-selected");
        watchlistBtn.getStyleClass().remove("nav-btn-selected");
        aboutBtn.getStyleClass().add("nav-btn-selected");
    }
}
