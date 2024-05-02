package at.ac.fhcampuswien.fhmdb.database;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "contact")
public class MovieEntity {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private String apiID;

    @DatabaseField()
    private String title;
    @DatabaseField
    private String description;
    @DatabaseField()
    private String genres;
    @DatabaseField()
    private int releaseYear;
    @DatabaseField()
    private double rating;


    public MovieEntity(){}

    public MovieEntity(int id, String apiID, String title, String description  ,String genres, int releaseYear, double rating) {
        this.id = id;
        this.apiID=apiID;
        this.title = title;
        this.description=description;
        this.genres = genres;
        this.releaseYear=releaseYear;
        this.rating=rating;

    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public List<String> getMainCast() {
        return mainCast;
    }

    public void setMainCast(List<String> mainCast) {
        this.mainCast = mainCast;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }*/
}
