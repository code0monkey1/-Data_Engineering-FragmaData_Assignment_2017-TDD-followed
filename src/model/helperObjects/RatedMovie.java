package model.helperObjects;

public class RatedMovie implements Comparable<RatedMovie> {


    private double rating;
    private int views;
    private int id;

    public RatedMovie(int id, double rating, int views) {
        this.id = id;
        this.rating = rating;
        this.views = views;
    }

    @Override
    public String toString() {
        return "RatedMovie{" +
                "rating=" + rating +
                ", views=" + views +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RatedMovie that = (RatedMovie) o;

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
    public int compareTo(RatedMovie ratedMovie) {
        return Double.compare(ratedMovie.rating, this.rating);
    }

    public int getViews() {
        return views;
    }
}
