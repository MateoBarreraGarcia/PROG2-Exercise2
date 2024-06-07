package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Screen;
import at.ac.fhcampuswien.fhmdb.factories.ControllerFactory;
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

    private static MainController instance;

    private MainController() {}

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public void initialize() {
        loadHomeView(); // when the app is started the home screen is first initialized
    }

    public boolean setContentView(String fxml){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        fxmlLoader.setControllerFactory(ControllerFactory.getInstance());
        try {
            mainPane.setCenter(fxmlLoader.load());
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while loading fxml: " + fxml);
        }
        return false;
    }

    /*
    The following three methods call setContentView with the corresponding fxml file path to load the new fxml into the scene
    After the fxml load was successful the nav buttons are updated to show on what page the user is
     */

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
