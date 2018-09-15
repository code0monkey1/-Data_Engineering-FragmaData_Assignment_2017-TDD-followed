package helperObjects;

public class MovieRating implements Comparable<MovieRating> {


    private int rating;
    private int views;
    private int id;

    public MovieRating(int id, int rating, int views) {
        this.id = id;
        this.rating = rating;
        this.views = views;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieRating that = (MovieRating) o;

        if (rating != that.rating) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = rating;
        result = 31 * result + id;
        return result;
    }

    public int getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(MovieRating movieRating) {
        return movieRating.rating - this.rating;
    }

    public int getViews() {
        return views;
    }
}
