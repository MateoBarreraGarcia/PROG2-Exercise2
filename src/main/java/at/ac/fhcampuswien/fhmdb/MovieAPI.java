package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MovieAPI {
    OkHttpClient client = new OkHttpClient();

    public List<Movie> getRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .addHeader("User-Agent", "User-Agent")
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.code());

        List<Movie> movies = new ArrayList<>(); //A

        if (response.code() == 200) {
            // the request was successful and is parsed into objects
            try {
                String jsonData = response.body().string();
                JSONArray jsonArray = new JSONArray(jsonData);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String title = jsonObject.getString("title");
                    String description = jsonObject.getString("description");
                    JSONArray genresArray = jsonObject.getJSONArray("genres");
                    String id = jsonObject.getString("id");
                    int releaseYear = jsonObject.getInt("releaseYear");
                    String imgUrl = jsonObject.optString("imgUrl", null);
                    int lengthInMinutes = jsonObject.optInt("lengthInMinutes", 0);
                    double rating = jsonObject.optDouble("rating", 0.0);
                    List<Genre> genres = new ArrayList<>();
                    for (int j = 0; j < genresArray.length(); j++) {
                        String genreString = genresArray.getString(j);
                        genres.add(Genre.valueOf(genreString));
                    }
                    JSONArray directorsArray = jsonObject.getJSONArray("directors");
                    List<String> directors = new ArrayList<>();
                    for (int j = 0; j < directorsArray.length(); j++) {
                        directors.add(directorsArray.getString(j));
                    }
                    JSONArray writersArray = jsonObject.getJSONArray("writers");
                    List<String> writers = new ArrayList<>();
                    for (int j = 0; j < writersArray.length(); j++) {
                        writers.add(writersArray.getString(j));
                    }
                    JSONArray mainCastArray = jsonObject.getJSONArray("mainCast");
                    List<String> mainCast = new ArrayList<>();
                    for (int j = 0; j < mainCastArray.length(); j++) {
                        mainCast.add(mainCastArray.getString(j));
                    }
                    movies.add(new Movie(title, description, genres, id, releaseYear, imgUrl, lengthInMinutes, directors, writers, mainCast, rating));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //String jsonData = response.body().string(); //A
            //System.out.println(response.body().string());
        } else {
            System.out.println("There was a problem with the request. Error-Code: " + response.code());
        }
        return movies; //A
    }

    public String generateRequestString() {
        return "https://prog2.fh-campuswien.ac.at/movies";
    }

    public String generateRequestString(String query, Object genre, int year, double rating) {
        StringBuilder url = new StringBuilder(generateRequestString());

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
            url.append("?");
            for (String s : params) {
                url.append("&").append(s);
            }
        }

        System.out.println(url);
        return url.toString();
    }

    public static void main(String[] args) throws IOException {
        MovieAPI movieAPI = new MovieAPI();
        String requestUrl = movieAPI.generateRequestString("", null, 0, 0);
        List<Movie> movies = movieAPI.getRequest(requestUrl);
        //movieAPI.getRequest("https://prog2.fh-campuswien.ac.at/movies");
        //movieAPI.generateRequestString("", null, 0, 0);
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
        }
    }
}


