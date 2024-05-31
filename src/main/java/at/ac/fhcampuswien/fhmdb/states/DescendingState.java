package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;

public class DescendingState extends State {
    public DescendingState(HomeController homeController) {
        super(homeController);
    }

    @Override
    public void sortMovies() {
        homeController.getObservableMovies().sort(Comparator.comparing(Movie::getTitle).reversed());
        homeController.sortBtn.setText("Sort (Desc)");
    }

    @Override
    public void onSortBtnClicked() {
        State ascendingState = new AscendingState(homeController);
        ascendingState.sortMovies();
        homeController.changeState(ascendingState);
    }
}
