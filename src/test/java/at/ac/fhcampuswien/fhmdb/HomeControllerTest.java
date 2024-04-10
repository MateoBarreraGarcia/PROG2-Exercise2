package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortedState;
import com.sun.source.tree.ArrayAccessTree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    private static HomeController homeController;
    private static List<Movie> testMovieData;

    @BeforeAll
    static void init()
    {
        homeController = new HomeController();
        testMovieData = Arrays.asList(
                new Movie("Interstellar",
                        "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
                        Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION), "8382349", 2014, "jhecbsdhc", 169,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nola"),
                        Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"), 8.7),
                new Movie("The Shawshank Redemption",
                        "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.",
                        Arrays.asList(Genre.DRAMA), "8382349", 1994, "jhecbsdhc", 144,
                        Arrays.asList("Frank Darabont"), Arrays.asList("Stephen King", "Frank Darabont"),
                        Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton"), 9.3),
                new Movie("Inception",
                        "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                        Arrays.asList(Genre.DRAMA), "8382349", 2010, "jhecbsdhc", 148,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nolan"),
                        Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"), 8.8),
                new Movie("The Godfather",
                        "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                        Arrays.asList(Genre.DRAMA), "", 1972, "", 175,
                        Arrays.asList("Francis Ford Coppola"), Arrays.asList("Mario Puzo", "Francis Ford Coppola"),
                        Arrays.asList("Marlon Brando", "Al Pacino", "James Caan"), 9.2),
                new Movie("The Lion King",
                        "Lion cub and future king Simba searches for his identity. His eagerness to please others and penchant for testing his boundaries sometimes gets him into trouble.",
                        Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.DRAMA, Genre.FAMILY, Genre.MUSICAL), "", 1994, "", 88,
                        Arrays.asList("Roger Allers", "Rob Minkoff"), Arrays.asList("Irene Mecchi", "Jonathan Roberts", "Linda Woolverton"),
                        Arrays.asList("Matthew Broderick", "Jeremy Irons", "James Earl Jones"), 8.5));
    }

    // The commented out tests are from the exercise 1 sample solution
    @Test
    void at_initialization_allMovies_and_observableMovies_should_be_filled_and_equal()
    {
        homeController.initializeState();
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }

    @Test
    void if_not_yet_sorted_sort_is_applied_in_ascending_order()
    {
        // given
        homeController.observableMovies.clear();
        homeController.observableMovies.addAll(testMovieData);
        homeController.sortedState = SortedState.NONE;

        // when
        homeController.sortMovies();

        // then
        List<Movie> expected = Arrays.asList(
                new Movie("Inception",
                        "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                        Arrays.asList(Genre.DRAMA), "8382349", 2010, "jhecbsdhc", 148,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nolan"),
                        Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"), 8.8),
                new Movie("Interstellar",
                        "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
                        Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION), "8382349", 2014, "jhecbsdhc", 169,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nola"),
                        Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"), 8.7),
                new Movie("The Godfather",
                        "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                        Arrays.asList(Genre.DRAMA), "", 1972, "", 175,
                        Arrays.asList("Francis Ford Coppola"), Arrays.asList("Mario Puzo", "Francis Ford Coppola"),
                        Arrays.asList("Marlon Brando", "Al Pacino", "James Caan"), 9.2),
                new Movie("The Lion King",
                        "Lion cub and future king Simba searches for his identity. His eagerness to please others and penchant for testing his boundaries sometimes gets him into trouble.",
                        Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.DRAMA, Genre.FAMILY, Genre.MUSICAL), "", 1994, "", 88,
                        Arrays.asList("Roger Allers", "Rob Minkoff"), Arrays.asList("Irene Mecchi", "Jonathan Roberts", "Linda Woolverton"),
                        Arrays.asList("Matthew Broderick", "Jeremy Irons", "James Earl Jones"), 8.5),
                new Movie("The Shawshank Redemption",
                        "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.",
                        Arrays.asList(Genre.DRAMA), "8382349", 1994, "jhecbsdhc", 144,
                        Arrays.asList("Frank Darabont"), Arrays.asList("Stephen King", "Frank Darabont"),
                        Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton"), 9.3));

        assertEquals(expected, homeController.observableMovies);
    }

    @Test
    void if_last_sort_ascending_next_sort_should_be_descending()
    {
        // given
        homeController.observableMovies.clear();
        homeController.observableMovies.addAll(testMovieData);
        homeController.sortedState = SortedState.ASCENDING;

        // when
        homeController.sortMovies();

        // then
        List<Movie> expected = Arrays.asList(
                new Movie("The Shawshank Redemption",
                        "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.",
                        Arrays.asList(Genre.DRAMA), "8382349", 1994, "jhecbsdhc", 144,
                        Arrays.asList("Frank Darabont"), Arrays.asList("Stephen King", "Frank Darabont"),
                        Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton"), 9.3),
                new Movie("The Lion King",
                        "Lion cub and future king Simba searches for his identity. His eagerness to please others and penchant for testing his boundaries sometimes gets him into trouble.",
                        Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.DRAMA, Genre.FAMILY, Genre.MUSICAL), "", 1994, "", 88,
                        Arrays.asList("Roger Allers", "Rob Minkoff"), Arrays.asList("Irene Mecchi", "Jonathan Roberts", "Linda Woolverton"),
                        Arrays.asList("Matthew Broderick", "Jeremy Irons", "James Earl Jones"), 8.5),
                new Movie("The Godfather",
                        "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                        Arrays.asList(Genre.DRAMA), "", 1972, "", 175,
                        Arrays.asList("Francis Ford Coppola"), Arrays.asList("Mario Puzo", "Francis Ford Coppola"),
                        Arrays.asList("Marlon Brando", "Al Pacino", "James Caan"), 9.2),
                new Movie("Interstellar",
                        "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
                        Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION), "8382349", 2014, "jhecbsdhc", 169,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nola"),
                        Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"), 8.7),
                new Movie("Inception",
                        "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                        Arrays.asList(Genre.DRAMA), "8382349", 2010, "jhecbsdhc", 148,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nolan"),
                        Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"), 8.8));

        assertEquals(expected, homeController.observableMovies);
    }

    @Test
    void if_last_sort_descending_next_sort_should_be_ascending()
    {
        // given
        homeController.observableMovies.clear();
        homeController.observableMovies.addAll(testMovieData);
        homeController.sortedState = SortedState.DESCENDING;

        // when
        homeController.sortMovies();

        // then
        List<Movie> expected = Arrays.asList(
                new Movie("Inception",
                        "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                        Arrays.asList(Genre.DRAMA), "8382349", 2010, "jhecbsdhc", 148,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nolan"),
                        Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"), 8.8),
                new Movie("Interstellar",
                        "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
                        Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION), "8382349", 2014, "jhecbsdhc", 169,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nola"),
                        Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"), 8.7),
                new Movie("The Godfather",
                        "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                        Arrays.asList(Genre.DRAMA), "", 1972, "", 175,
                        Arrays.asList("Francis Ford Coppola"), Arrays.asList("Mario Puzo", "Francis Ford Coppola"),
                        Arrays.asList("Marlon Brando", "Al Pacino", "James Caan"), 9.2),
                new Movie("The Lion King",
                        "Lion cub and future king Simba searches for his identity. His eagerness to please others and penchant for testing his boundaries sometimes gets him into trouble.",
                        Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.DRAMA, Genre.FAMILY, Genre.MUSICAL), "", 1994, "", 88,
                        Arrays.asList("Roger Allers", "Rob Minkoff"), Arrays.asList("Irene Mecchi", "Jonathan Roberts", "Linda Woolverton"),
                        Arrays.asList("Matthew Broderick", "Jeremy Irons", "James Earl Jones"), 8.5),
                new Movie("The Shawshank Redemption",
                        "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.",
                        Arrays.asList(Genre.DRAMA), "8382349", 1994, "jhecbsdhc", 144,
                        Arrays.asList("Frank Darabont"), Arrays.asList("Stephen King", "Frank Darabont"),
                        Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton"), 9.3));

        assertEquals(expected, homeController.observableMovies);

    }

    @Test
    void query_filter_matches_with_lower_and_uppercase_letters()
    {
        // given
        String query = "Lion";

        // when
        List<Movie> actual = homeController.filterByQuery(testMovieData, query);

        // then
        List<Movie> expected = Arrays.asList(
                new Movie("The Lion King",
                        "Lion cub and future king Simba searches for his identity. His eagerness to please others and penchant for testing his boundaries sometimes gets him into trouble.",
                        Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.DRAMA, Genre.FAMILY, Genre.MUSICAL), "", 1994, "", 88,
                        Arrays.asList("Roger Allers", "Rob Minkoff"), Arrays.asList("Irene Mecchi", "Jonathan Roberts", "Linda Woolverton"),
                        Arrays.asList("Matthew Broderick", "Jeremy Irons", "James Earl Jones"), 8.5));

        assertEquals(expected, actual);
    }

    @Test
    void query_filter_with_null_movie_list_throws_exception()
    {
        // given
        String query = "IfE";

        // when and then
        assertThrows(IllegalArgumentException.class, () -> homeController.filterByQuery(null, query));
    }

    @Test
    void query_filter_with_null_value_returns_unfiltered_list()
    {
        // given
        String query = null;

        // when
        List<Movie> actual = homeController.filterByQuery(testMovieData, query);

        // then
        assertEquals(testMovieData, actual);
    }

    @Test
    void genre_filter_with_null_value_returns_unfiltered_list()
    {
        // given
        Genre genre = null;

        // when
        List<Movie> actual = homeController.filterByGenre(testMovieData, genre);

        // then
        assertEquals(testMovieData, actual);
    }

    @Test
    void genre_filter_returns_all_movies_containing_given_genre()
    {
        // given
        Genre genre = Genre.ADVENTURE;

        // when
        List<Movie> actual = homeController.filterByGenre(testMovieData, genre);

        // then
        assertEquals(2, actual.size());
    }

    @Test
    void no_filtering_ui_if_empty_query_or_no_genre_is_set()
    {
        // given
        homeController.allMovies = testMovieData;
        homeController.observableMovies.clear();
        homeController.observableMovies.addAll(homeController.allMovies);

        // when
        homeController.applyAllFilters("", null);

        // then
        assertEquals(testMovieData, homeController.observableMovies);
    }


    /*
    The following tests are for the stream methods
     */

    @Test
    void test_Get_Longest_Movie_Title()
    {

        // given and when
        int longestTitleLength = homeController.getLongestMovieTitle(testMovieData);

        //then
        assertEquals(24, longestTitleLength);

    }

    @Test
    void test_count_movies_from()
    {

        //given
        String director = "Christopher Nolan";

        //when
        long moviesByChristopherNolan = homeController.countMoviesFrom(testMovieData, director);

        //then
        assertEquals(2, moviesByChristopherNolan);

    }

    @Test
    void get_between_years_returns_all_movies_released_between_start_year_and_end_year()
    {
        // given
        int startYear = 2000;
        int endYear = 2018;

        // when
        List<Movie> actual = homeController.getMoviesBetweenYears(testMovieData, startYear, endYear);

        // then
        List<Movie> expected = Arrays.asList(
                new Movie("Interstellar",
                        "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
                        Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION), "8382349", 2014, "jhecbsdhc", 169,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nola"),
                        Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"), 8.7),
                new Movie("Inception",
                        "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                        Arrays.asList(Genre.DRAMA), "8382349", 2010, "jhecbsdhc", 148,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nolan"),
                        Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"), 8.8));

        assertEquals(actual, expected);
    }

    @Test
    void get_between_years_with_same_start_year_and_end_year_returns_all_movies_released_that_year()
    {
        // given
        int startYear = 1994;
        int endYear = 1994;

        // when
        List<Movie> actual = homeController.getMoviesBetweenYears(testMovieData, startYear, endYear);

        // then
        List<Movie> expected = Arrays.asList(
                new Movie("The Shawshank Redemption",
                        "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.",
                        Arrays.asList(Genre.DRAMA), "8382349", 1994, "jhecbsdhc", 144,
                        Arrays.asList("Frank Darabont"), Arrays.asList("Stephen King", "Frank Darabont"),
                        Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton"), 9.3),
                new Movie("The Lion King",
                        "Lion cub and future king Simba searches for his identity. His eagerness to please others and penchant for testing his boundaries sometimes gets him into trouble.",
                        Arrays.asList(Genre.ANIMATION, Genre.ADVENTURE, Genre.DRAMA, Genre.FAMILY, Genre.MUSICAL), "", 1994, "", 88,
                        Arrays.asList("Roger Allers", "Rob Minkoff"), Arrays.asList("Irene Mecchi", "Jonathan Roberts", "Linda Woolverton"),
                        Arrays.asList("Matthew Broderick", "Jeremy Irons", "James Earl Jones"), 8.5));

        assertEquals(actual, expected);
    }

    @Test
    void get_between_years_with_greater_start_year_than_end_year_throws_exception()
    {
        // given
        int startYear = 2000;
        int endYear = 1953;

        // when and then
        assertThrows(IllegalArgumentException.class, () -> homeController.getMoviesBetweenYears(testMovieData, startYear, endYear));
    }

    @Test
    void get_most_popular_actor_one_actor()
    {
        // given
        List<Movie> extendedTestDataList = new ArrayList<>(testMovieData);
        extendedTestDataList.addAll(Arrays.asList(
                new Movie("The Lord of the Rings: The Return of the King",
                        "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
                        Arrays.asList(Genre.DRAMA, Genre.FANTASY, Genre.ADVENTURE), "a47afd8a-b768-4a34-8ed6-bf5d90c0feeb", 2003, "https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg", 201,
                        Arrays.asList("Peter Jackson"), Arrays.asList("J.R.R. Tolkien", "Fran Walsh", "Philippa Boyens"),
                        Arrays.asList("Elijah Wood", "Ian McKellen", "Viggo Mortensen"), 8.9),

                new Movie("The Lord of the Rings: The Two Towers",
                        "While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron\"s new ally, Saruman, and his hordes of Isengard.",
                        Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.FANTASY), "e109ec5f-c3af-472d-93e5-9c4c0d04d640", 2002, "https://m.media-amazon.com/images/M/MV5BZTUxNzg3MDUtYjdmZi00ZDY1LTkyYTgtODlmOGY5N2RjYWUyXkEyXkFqcGdeQXVyMTA0MTM5NjI2._V1_FMjpg_UX1000_.jpg", 179,
                        Arrays.asList("Peter Jackson"), Arrays.asList("J.R.R. Tolkien", "Fran Walsh", "Philippa Boyens"),
                        Arrays.asList("Elijah Wood"), 8.7)));


        // when
        String actual = homeController.getMostPopularActor(extendedTestDataList);

        // then
        String expected = "Elijah Wood";

        assertEquals(expected, actual);
    }

    @Test
    void get_most_popular_actor_multiple_most_popular_actors()
    {
        // given
        List<Movie> extendedTestDataList = new ArrayList<>(testMovieData);
        extendedTestDataList.addAll(Arrays.asList(
                new Movie("The Lord of the Rings: The Return of the King",
                        "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
                        Arrays.asList(Genre.DRAMA, Genre.FANTASY, Genre.ADVENTURE), "a47afd8a-b768-4a34-8ed6-bf5d90c0feeb", 2003, "https://m.media-amazon.com/images/M/MV5BNzA5ZDNlZWMtM2NhNS00NDJjLTk4NDItYTRmY2EwMWZlMTY3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_FMjpg_UX1000_.jpg", 201,
                        Arrays.asList("Peter Jackson"), Arrays.asList("J.R.R. Tolkien", "Fran Walsh", "Philippa Boyens"),
                        Arrays.asList("Elijah Wood", "Ian McKellen", "Viggo Mortensen"), 8.9),

                new Movie("The Lord of the Rings: The Two Towers",
                        "While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron\"s new ally, Saruman, and his hordes of Isengard.",
                        Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.FANTASY), "e109ec5f-c3af-472d-93e5-9c4c0d04d640", 2002, "https://m.media-amazon.com/images/M/MV5BZTUxNzg3MDUtYjdmZi00ZDY1LTkyYTgtODlmOGY5N2RjYWUyXkEyXkFqcGdeQXVyMTA0MTM5NjI2._V1_FMjpg_UX1000_.jpg", 179,
                        Arrays.asList("Peter Jackson"), Arrays.asList("J.R.R. Tolkien", "Fran Walsh", "Philippa Boyens"),
                        Arrays.asList("Elijah Wood", "Ian McKellen", "Viggo Mortensen"), 8.7)));

        // when
        String actual = homeController.getMostPopularActor(extendedTestDataList);

        // then
        String expected = "Viggo Mortensen";

        assertEquals(expected, actual);
    }


    @Test
    void get_most_popular_actor_no_actors()
    {
        // given
        List<Movie> tempTestDataList = Arrays.asList(new Movie("Interstellar",
                        "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.",
                        Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION), "8382349", 2014, "jhecbsdhc", 169,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nola"), Collections.emptyList(), 8.7),
                new Movie("The Shawshank Redemption",
                        "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.",
                        Arrays.asList(Genre.DRAMA), "8382349", 1994, "jhecbsdhc", 144,
                        Arrays.asList("Frank Darabont"), Arrays.asList("Stephen King", "Frank Darabont"),
                        Collections.emptyList(), 9.3),
                new Movie("Inception",
                        "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                        Arrays.asList(Genre.DRAMA), "8382349", 2010, "jhecbsdhc", 148,
                        Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nolan"),
                        Collections.emptyList(), 8.8),
                new Movie("The Godfather",
                        "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                        Arrays.asList(Genre.DRAMA), "", 1972, "", 175,
                        Arrays.asList("Francis Ford Coppola"), Arrays.asList("Mario Puzo", "Francis Ford Coppola"),
                        Collections.emptyList(), 9.2));

        // when
        String actual = homeController.getMostPopularActor(tempTestDataList);

        // then
        String expected = "";

        assertEquals(expected, actual);
    }
}