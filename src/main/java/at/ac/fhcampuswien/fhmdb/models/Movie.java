package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private final String title;
    private final String description;
    private final List<Genre> genres;

    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Movie other)) {
            return false;
        }
        return this.title.equals(other.title) && this.description.equals(other.description) && this.genres.equals(other.genres);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(
                "Life Is Beautiful",
                "When an open-minded Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp." ,
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie(
                "The Usual Suspects",
                "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup.",
                Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.MYSTERY)));
        movies.add(new Movie(
                "Puss in Boots",
                "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town.",
                Arrays.asList(Genre.COMEDY, Genre.FAMILY, Genre.ANIMATION)));
        movies.add(new Movie(
                "Avatar",
                "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ACTION)));
        movies.add(new Movie(
                "The Wolf of Wall Street",
                "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.",
                Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.BIOGRAPHY)));
        movies.add(new Movie(
                "Damsel",
                "A dutiful damsel agrees to marry a handsome prince, only to find the royal family has recruited her as a sacrifice to repay an ancient debt.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY)));
        movies.add(new Movie(
                "Oppenheimer",
                "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY)));
        movies.add(new Movie(
                "Wonka",
                "With dreams of opening a shop in a city renowned for its chocolate, a young and poor Willy Wonka discovers that the industry is run by a cartel of greedy chocolatiers.",
                Arrays.asList(Genre.ADVENTURE, Genre.COMEDY, Genre.FAMILY)));
        movies.add(new Movie(
                "Django Unchained",
                "With the help of a German bounty-hunter, a freed slave sets out to rescue his wife from a brutal plantation owner in Mississippi.",
                Arrays.asList(Genre.DRAMA, Genre.WESTERN)));
        movies.add(new Movie(
                "Dangal",
                "Former wrestler Mahavir Singh Phogat and his two wrestler daughters struggle towards glory at the Commonwealth Games in the face of societal oppression.",
                Arrays.asList(Genre.ACTION, Genre.BIOGRAPHY, Genre.DRAMA)));
        movies.add(new Movie(
                "La La Land",
                "While navigating their careers in Los Angeles, a pianist and an actress fall in love while attempting to reconcile their aspirations for the future.",
                Arrays.asList(Genre.COMEDY, Genre.DRAMA, Genre.MUSICAL)));
        movies.add(new Movie(
                "Inception",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                Arrays.asList(Genre.ACTION, Genre.ADVENTURE, Genre.SCIENCE_FICTION)));
        movies.add(new Movie(
                "Mirror Game",
                "A struggling actor becomes a professional impostor, hired to impersonate people for financial gain, but as he delves deeper into his work he becomes embroiled in trouble that threatens to unravel his life.",
                Arrays.asList(Genre.CRIME, Genre.MYSTERY, Genre.THRILLER)));
        movies.add(new Movie(
                "Eternal Sunshine of the Spotless Mind",
                "When their relationship turns sour, a couple undergoes a medical procedure to have each other erased from their memories for ever.",
                Arrays.asList(Genre.ROMANCE, Genre.SCIENCE_FICTION, Genre.DRAMA)));
        movies.add(new Movie(
                "The Lion King",
                "Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.",
                Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.DRAMA)));
        movies.add(new Movie(
                "Our Planet",
                "Explores and unravels the mystery of how and why animals migrate, showing some of the most dramatic and compelling stories in the natural world through spectacular and innovative cinematography.",
                Arrays.asList(Genre.DOCUMENTARY)));
        movies.add(new Movie(
                "Incendies",
                "Twins journey to the Middle East to discover their family history and fulfill their mother's last wishes.",
                Arrays.asList(Genre.DRAMA, Genre.MYSTERY, Genre.WAR)));

        return movies;
    }
}
