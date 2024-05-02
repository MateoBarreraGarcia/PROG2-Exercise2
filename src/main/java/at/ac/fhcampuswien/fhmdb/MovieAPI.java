package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.exceptions.MovieApiException;
import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MovieAPI {
    OkHttpClient client = new OkHttpClient();

    public List<Movie> getMoviesRequest(String url) throws MovieApiException {
        Request request = new Request.Builder()
                .addHeader("User-Agent", "User-Agent")
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();

            List<Movie> movies = new ArrayList<>(); //A

            if (response.code() == 200) {
                // the request was successful and is parsed into objects

                String jsonData = response.body().string(); //nimmt den JSONSTRING aus der Antwort
                JSONArray jsonArray = new JSONArray(jsonData); //erstellt ein JSONARRAY aus dem JSONSTRING

                //durchläuft das JSONARRAY und erstellt für den Eintrag ein Movie-Objekt
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i); //holt den akutuellen JSON-Eintrag
                    //nimmt die Daten des Films aus dem JSON-Objekt
                    String title = jsonObject.getString("title");
                    String description = jsonObject.getString("description");
                    JSONArray genresArray = jsonObject.getJSONArray("genres");
                    String id = jsonObject.getString("id");
                    int releaseYear = jsonObject.getInt("releaseYear");
                    String imgUrl = jsonObject.optString("imgUrl", null);
                    int lengthInMinutes = jsonObject.optInt("lengthInMinutes", 0);
                    double rating = jsonObject.optDouble("rating", 0.0);
                    //durchläuft das JSONARRAY der Genres und sie fügt der Genre-Liste hinzu
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

                //String jsonData = response.body().string(); //A
                //System.out.println(response.body().string());
            } else {
                System.out.println("HTTP response: " + response.code());
                throw new MovieApiException("HTTP response: " + response.code());
            }

            System.out.println("HTTP response: " + response.code());

            return movies; //gibt die Liste der Movie-Objekte zurück
        } catch (IOException e) { // if the request execute at line 28 fails
            throw new MovieApiException("API request failed");
        } catch (JSONException e) {
            throw new MovieApiException("Parsing of JSON data failed");
        }
    }

    public String generateRequestString() {
        return "https://prog2.fh-campuswien.ac.at/movies";
    }

    public String generateRequestString(String query, Object genre, Integer year, Double rating) {
        StringBuilder url = new StringBuilder(generateRequestString());

        List<String> params = new ArrayList<>();

        if (query != null && !query.isEmpty()) {
            params.add("query=" + query);
        }
        if (genre != null && genre != Genre.ALL_GENRES) {
            params.add("genre=" + genre);
        }
        if (year != null) {
            params.add("releaseYear=" + year);
        }
        if (rating != null) {
            params.add("ratingFrom=" + rating);
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


