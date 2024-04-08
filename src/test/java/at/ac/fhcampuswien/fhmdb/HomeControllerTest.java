package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.models.SortedState;
import com.sun.source.tree.ArrayAccessTree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {
    private static HomeController homeController;
    private static List<Movie> TestMovieData;
    @BeforeAll
    static void init() {
        homeController = new HomeController();
        TestMovieData = new ArrayList<>();

        TestMovieData.add(new Movie("Test Film 1", "This is a Test Film",new ArrayList<Genre>(){{add(Genre.ACTION); add(Genre.ANIMATION);}},"123456",2000,".\\Image\\TestImage1",115,new ArrayList<>(){{add("Director 1-1");add("Director 1-2");}},new ArrayList<>(){{add("Writer 1-1");add("Writer 1-2");}},new ArrayList<>(){{add("MainCast 1-1");add("MainCast 1-2");}}, 0.0 ));
        TestMovieData.add(new Movie("Test Film 2", "This is a Test Film",new ArrayList<Genre>(){{add(Genre.COMEDY); add(Genre.CRIME);}},"374849345",1937,".\\Image\\TestImage2",60,new ArrayList<>(){{add("Director 2-1");add("Director 2-2");}},new ArrayList<>(){{add("Writer 2-1");add("Writer 2-2");}},new ArrayList<>(){{add("MainCast 1-1");add("MainCast 2-2");}}, 1.0 ));
        TestMovieData.add(new Movie("Test Film 3", "This is a Test Film",new ArrayList<Genre>(){{add(Genre.FANTASY); add(Genre.BIOGRAPHY);}},"703234",2024,".\\Image\\TestImage3",70,new ArrayList<>(){{add("Director 3-1");add("Director 3-2");}},new ArrayList<>(){{add("Writer 1-1");add("Writer 3-2");}},new ArrayList<>(){{add("MainCast 3-1");add("MainCast 1-2");}}, 9.9 ));
        TestMovieData.add(new Movie("Test Film 4", "This is a Test Film",new ArrayList<Genre>(){{add(Genre.ACTION); add(Genre.ROMANCE);}},"4920453",2003,".\\Image\\TestImage4",320,new ArrayList<>(){{add("Director 1-1");add("Director 4-2");}},new ArrayList<>(){{add("Writer 4-1");add("Writer 4-2");}},new ArrayList<>(){{add("MainCast 2-1");add("MainCast 4-2");}}, 3.9 ));
        TestMovieData.add(new Movie("Test Film 5", "This is a Test Film",new ArrayList<Genre>(){{add(Genre.HISTORY); add(Genre.MUSICAL);}},"938045",1980,".\\Image\\TestImage5",160,new ArrayList<>(){{add("Director 5-1");add("Director 5-2");}},new ArrayList<>(){{add("Writer 5-1");add("Writer 3-2");}},new ArrayList<>(){{add("MainCast 4-1");add("MainCast 6-2");}}, 4.0 ));
        TestMovieData.add(new Movie("Test Film 6", "This is a Test Film",new ArrayList<Genre>(){{add(Genre.DOCUMENTARY); add(Genre.MYSTERY);}},"293052",1906,".\\Image\\TestImage6",78,new ArrayList<>(){{add("Director 6-1");add("Director 2-2");}},new ArrayList<>(){{add("Writer 6-1");add("Writer 5-2");}},new ArrayList<>(){{add("MainCast 5-1");add("MainCast 4-2");}}, 7.3 ));
        TestMovieData.add(new Movie("Test Film 7", "This is a Test Film",new ArrayList<Genre>(){{add(Genre.FAMILY); add(Genre.DOCUMENTARY);}},"9302354",1998,".\\Image\\TestImage7",49,new ArrayList<>(){{add("Director 4-2");add("Director 7-2");}},new ArrayList<>(){{add("Writer 2-1");add("Writer 3-2");}},new ArrayList<>(){{add("MainCast 3-1");add("MainCast 3-2");}}, 6.1 ));



    }

    @Test
    void at_initialization_allMovies_and_observableMovies_should_be_filled_and_equal() {
        homeController.initializeState();
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }

    @Test
    void if_not_yet_sorted_sort_is_applied_in_ascending_order() {
        // given
        homeController.initializeState();
        homeController.sortedState = SortedState.NONE;

        // when
        homeController.sortMovies();

        // then
        // TODO: Replace with
        List<Movie> expected = Arrays.asList();
        /*
        List<Movie> expected = Arrays.asList(
                new Movie(
                        "Avatar",
                        "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                        Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ACTION)),
                new Movie(
                        "Life Is Beautiful",
                        "When an open-minded Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp." ,
                        Arrays.asList(Genre.DRAMA, Genre.ROMANCE)),
                new Movie(
                        "Puss in Boots",
                        "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town.",
                        Arrays.asList(Genre.COMEDY, Genre.FAMILY, Genre.ANIMATION)),
                new Movie(
                        "The Usual Suspects",
                        "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup.",
                        Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.MYSTERY)),
                new Movie(
                        "The Wolf of Wall Street",
                        "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.",
                        Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.BIOGRAPHY))

        );
        */

        assertEquals(expected, homeController.observableMovies);

    }

    @Test
    void if_last_sort_ascending_next_sort_should_be_descending() {
        // given
        homeController.initializeState();
        homeController.sortedState = SortedState.ASCENDING;

        // when
        homeController.sortMovies();

        // then
        List<Movie> expected = Arrays.asList();
        /*
                new Movie(
                        "The Wolf of Wall Street",
                        "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.",
                        Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.BIOGRAPHY)),
                new Movie(
                        "The Usual Suspects",
                        "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup.",
                        Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.MYSTERY)),
                new Movie(
                        "Puss in Boots",
                        "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town.",
                        Arrays.asList(Genre.COMEDY, Genre.FAMILY, Genre.ANIMATION)),
                new Movie(
                        "Life Is Beautiful",
                        "When an open-minded Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp." ,
                        Arrays.asList(Genre.DRAMA, Genre.ROMANCE)),
                new Movie(
                        "Avatar",
                        "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                        Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ACTION))
        );
        */

        assertEquals(expected, homeController.observableMovies);
    }

    @Test
    void if_last_sort_descending_next_sort_should_be_ascending() {
        // given
        homeController.initializeState();
        homeController.sortedState = SortedState.DESCENDING;

        // when
        homeController.sortMovies();

        // then
        List<Movie> expected = Arrays.asList();
        /*
                new Movie(
                        "Avatar",
                        "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.",
                        Arrays.asList(Genre.ANIMATION, Genre.DRAMA, Genre.ACTION)),
                new Movie(
                        "Life Is Beautiful",
                        "When an open-minded Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp." ,
                        Arrays.asList(Genre.DRAMA, Genre.ROMANCE)),
                new Movie(
                        "Puss in Boots",
                        "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town.",
                        Arrays.asList(Genre.COMEDY, Genre.FAMILY, Genre.ANIMATION)),
                new Movie(
                        "The Usual Suspects",
                        "A sole survivor tells of the twisty events leading up to a horrific gun battle on a boat, which begin when five criminals meet at a seemingly random police lineup.",
                        Arrays.asList(Genre.CRIME, Genre.DRAMA, Genre.MYSTERY)),
                new Movie(
                        "The Wolf of Wall Street",
                        "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.",
                        Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.BIOGRAPHY))

        );
        */

        assertEquals(expected, homeController.observableMovies);

    }

    @Test
    void query_filter_matches_with_lower_and_uppercase_letters(){
        // given
        homeController.initializeState();
        String query = "IfE";

        // when
        List<Movie> actual = homeController.filterByQuery(homeController.observableMovies, query);

        // then
        List<Movie> expected = Arrays.asList();
        /*
                new Movie(
                        "Life Is Beautiful",
                        "When an open-minded Jewish librarian and his son become victims of the Holocaust, he uses a perfect mixture of will, humor, and imagination to protect his son from the dangers around their camp." ,
                        Arrays.asList(Genre.DRAMA, Genre.ROMANCE)),
                new Movie(
                        "The Wolf of Wall Street",
                        "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.",
                        Arrays.asList(Genre.DRAMA, Genre.ROMANCE, Genre.BIOGRAPHY))
        );

         */

        assertEquals(expected, actual);
    }

    @Test
    void query_filter_with_null_movie_list_throws_exception(){
        // given
        homeController.initializeState();
        String query = "IfE";

        // when and then
        assertThrows(IllegalArgumentException.class, () -> homeController.filterByQuery(null, query));
    }

    @Test
    void query_filter_with_null_value_returns_unfiltered_list() {
        // given
        homeController.initializeState();
        String query = null;

        // when
        List<Movie> actual = homeController.filterByQuery(homeController.observableMovies, query);

        // then
        assertEquals(homeController.observableMovies, actual);
    }

    @Test
    void genre_filter_with_null_value_returns_unfiltered_list() {
        // given
        homeController.initializeState();
        Genre genre = null;

        // when
        List<Movie> actual = homeController.filterByGenre(homeController.observableMovies, genre);

        // then
        assertEquals(homeController.observableMovies, actual);
    }

    @Test
    void genre_filter_returns_all_movies_containing_given_genre() {
        // given
        homeController.initializeState();
        Genre genre = Genre.DRAMA;

        // when
        List<Movie> actual = homeController.filterByGenre(homeController.observableMovies, genre);

        // then
        assertEquals(4, actual.size());
    }

    @Test
    void no_filtering_ui_if_empty_query_or_no_genre_is_set() {
        // given
        homeController.initializeState();

        // when
        homeController.applyAllFilters("", null);

        // then
        assertEquals(homeController.allMovies, homeController.observableMovies);
    }

    @Test
    void test_Get_Longest_Movie_Title(){
        //given
        List<Movie> movies = Arrays.asList(
                new Movie("Interstellar", "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.", Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION), "8382349", 2014, "jhecbsdhc", 169, Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nola"), Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"), 8.7),
                new Movie("The Shawshank Redemtipn", "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.", Arrays.asList(Genre.DRAMA), "8382349", 1994, "jhecbsdhc", 144, Arrays.asList("Frank Darabont"), Arrays.asList("Stephen King","Frank Darabont"), Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton"), 9.3),
                new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.", Arrays.asList(Genre.DRAMA), "8382349", 2010, "jhecbsdhc", 148, Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"), 8.8));

                HomeController controller = new HomeController();

        //when
        int longestTitleLength = controller.getLongestMovieTitle(movies);

        //then
        assertEquals(24, longestTitleLength);


    }@Test
    void test_count_movies_from (){

        //given
        List<Movie> movies = Arrays.asList(
                        new Movie("Interstellar", "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.", Arrays.asList(Genre.ADVENTURE, Genre.DRAMA, Genre.SCIENCE_FICTION), "8382349", 2014, "jhecbsdhc", 169, Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nola"), Arrays.asList("Matthew McConaughey", "Anne Hathaway", "Jessica Chastain"), 8.7),
                        new Movie("The Shawshank Redemtipn", "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.", Arrays.asList(Genre.DRAMA), "8382349", 1994, "jhecbsdhc", 144, Arrays.asList("Frank Darabont"), Arrays.asList("Stephen King","Frank Darabont"), Arrays.asList("Tim Robbins", "Morgan Freeman", "Bob Gunton"), 9.3),
                        new Movie("Inception", "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.", Arrays.asList(Genre.DRAMA), "8382349", 2010, "jhecbsdhc", 148, Arrays.asList("Christopher Nolan"), Arrays.asList("Christopher Nolan"), Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt", "Elliot Page"), 8.8));

        HomeController controller = new HomeController();

        //when
        long moviesByChristopherNolan = controller.countMoviesFrom(movies, "Christopher Nolan");

        //then
        assertEquals(2, moviesByChristopherNolan);



    }

    @Test
    void get_between_years_returns_all_movies_released_between_start_year_and_end_year() {

    }

    @Test
    void get_between_years_with_null_end_year_returns_all_movies_released_after_start_year() {

    }

    @Test
    void get_between_years_with_null_start_year_returns_all_movies_released_before_end_year() {

    }

}