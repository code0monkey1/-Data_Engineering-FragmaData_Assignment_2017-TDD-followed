package model.helperObjects;

public class MovieRating implements Comparable<MovieRating> {


    private double rating;
    private int views;
    private int id;

    public MovieRating(int id, double rating, int views) {
        this.id = id;
        this.rating = rating;
        this.views = views;
    }

    @Override
    public String toString() {
        return "MovieRating{" +
                "rating=" + rating +
                ", views=" + views +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieRating that = (MovieRating) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(MovieRating movieRating) {
        return Double.compare(movieRating.rating, this.rating);
    }

    public int getViews() {
        return views;
    }
}
