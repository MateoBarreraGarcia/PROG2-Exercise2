package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieAPI {
    OkHttpClient client = new OkHttpClient();

    public void getRequest(String url) throws IOException { // TODO this should return a List<Movie> once the parsing was added
        Request request = new Request.Builder()
                .addHeader("User-Agent", "User-Agent")
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.code());

        if (response.code() == 200) {
            // TODO after a successful request parse the JSON into movie objects
            System.out.println(response.body().string());
        } else {
            System.out.println("There was a problem with the request. Error-Code: " + response.code());
        }
    }

    public String generateRequestString(String query, Object genre, int year, double rating) {
        String url = "https://prog2.fh-campuswien.ac.at/movies";

        List<String> params = new ArrayList<>();

        if (!query.isEmpty()) {
            params.add("query=" + query);
        }
        if (genre != null && !genre.toString().equals("No filter")) {
            params.add("genre=" + genre);
        }
        if (year != 0) {
            params.add("year=" + year);
        }
        if (rating != 0) {
            params.add("ratingFrom=" + rating);
        }

        if (!params.isEmpty()) { // add the queries to the base url
            url = url + "?";
            for (String s : params) {
                url = url + "&" + s;
            }
        }

        System.out.println(url);
        return url;
    }

    public static void main(String[] args) throws IOException {
        MovieAPI movieAPI = new MovieAPI();

        //movieAPI.getRequest("https://prog2.fh-campuswien.ac.at/movies");

        movieAPI.generateRequestString("", null, 0, 0);
    }
}


