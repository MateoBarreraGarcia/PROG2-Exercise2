package at.ac.fhcampuswien.fhmdb.Interfaces;

import at.ac.fhcampuswien.fhmdb.models.Movie;

public interface ObserverWatchListMovies<T>{
    void update(Movie movie, boolean success);
}
