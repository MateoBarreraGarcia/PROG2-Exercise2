package at.ac.fhcampuswien.fhmdb.Interfaces;

import at.ac.fhcampuswien.fhmdb.models.Movie;

public interface SubjectWatchListMovies {
    void addObserver(ObserverWatchListMovies observerWatchListMovies);
    void removeObserver(ObserverWatchListMovies observerWatchListMovies);
    void notifyObserver(Movie movie, boolean success);
}
