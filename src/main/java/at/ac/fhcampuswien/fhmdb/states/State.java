package at.ac.fhcampuswien.fhmdb.states;

import at.ac.fhcampuswien.fhmdb.HomeController;

public abstract class State {
    /**
     * Every state saves the instance of the homeController (through the constructor) for context data if needed.
     * E.g. if the state should change after a certain method implementation of one of the states was called
     */
    HomeController homeController;

    public State(HomeController homeController) {
        this.homeController = homeController;
    }

    public abstract void sortMovies();
    public abstract void onSortBtnClicked();
}
