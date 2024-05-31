package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;

import java.util.Comparator;

public class AscendingState extends State {
    public AscendingState(HomeController homeController) {
        super(homeController);
    }

    @Override
    public void sortMovies() {
        homeController.getObservableMovies().sort(Comparator.comparing(Movie::getTitle));
        homeController.sortBtn.setText("Sort (Asc)");
    }

    @Override
    public void onSortBtnClicked() {
        State descendingState = new DescendingState(homeController);
        descendingState.sortMovies();
        homeController.changeState(descendingState);
    }
}
