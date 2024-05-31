package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;

public class NoSortingState extends State{
    public NoSortingState(HomeController homeController) {
        super(homeController);
    }

    @Override
    public void sortMovies() {
        homeController.sortBtn.setText("Sort (None)");
    }

    @Override
    public void onSortBtnClicked() {
        State ascendingState = new AscendingState(homeController);
        ascendingState.sortMovies();
        homeController.changeState(ascendingState);
    }
}
