/**
 * Represents a film being shown in a cinema hall.
 */
public class Movie {
    private String name;
    private String rating;
    private String director;
    private boolean is3D;

    public Movie(String name, String rating, String director, boolean is3D) {
        this.name = name;
        this.rating = rating;
        this.director = director;
        this.is3D = is3D;
    }

    /**
     * Get the name of the film.
     *
     * @return The name of the film.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the rating of the film.
     *
     * @return The film's rating.
     */
    public String getRating() {
        return rating;
    }

    /**
     * Get the director of the film.
     *
     * @return The film's director.
     */
    public String getDirector() {
        return director;
    }

    /**
     * Check if the film is in 3D.
     *
     * @return True if the film is in 3D, false otherwise.
     */
    public boolean is3D() {
        return is3D;
    }
}
