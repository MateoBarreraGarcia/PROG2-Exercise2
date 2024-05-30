package at.ac.fhcampuswien.fhmdb.builders;

import at.ac.fhcampuswien.fhmdb.models.Genre;

import java.util.ArrayList;
import java.util.List;

public class MovieAPIRequestBuilder {
    public final static String ALL_MOVIES_PATH = "https://prog2.fh-campuswien.ac.at/movies";

    private String base;
    private String query;
    private String genre;
    private Integer releaseYear;
    private Double ratingFrom;

    public MovieAPIRequestBuilder(String base) {
        this.base = base;
    }

    public MovieAPIRequestBuilder query(String query) {
        this.query = query;
        return this;
    }

    public MovieAPIRequestBuilder genre(String genre) {
        this.genre = genre;
        return this;
    }

    public MovieAPIRequestBuilder releaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public MovieAPIRequestBuilder ratingFrom(Double ratingFrom) {
        this.ratingFrom = ratingFrom;
        return this;
    }

    public String build() {
        StringBuilder url = new StringBuilder(base);

        List<String> params = new ArrayList<>();

        if (query != null && !query.isEmpty()) {
            params.add("query=" + query);
        }
        if (genre != null && !genre.equals(Genre.ALL_GENRES.toString())) {
            params.add("genre=" + genre);
        }
        if (releaseYear != null) {
            params.add("releaseYear=" + releaseYear);
        }
        if (ratingFrom != null) {
            params.add("ratingFrom=" + ratingFrom);
        }

        if (!params.isEmpty()) { // add the queries to the base url
            url.append("?");
            for (String s : params) {
                if (params.indexOf(s) != 0) {
                    url.append("&");
                }
                url.append(s);
            }
        }

        System.out.println(url);
        return url.toString();
    }
}
