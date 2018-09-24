package model.helperObjects;

public class ViewedMovie implements Comparable<ViewedMovie> {


    private int movieID;
    private int count;

    public ViewedMovie(int movieID, int count) {
        this.movieID = movieID;
        this.count = count;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewedMovie viewedMovie = (ViewedMovie) o;

        return movieID == viewedMovie.movieID;
    }

    @Override
    public int hashCode() {
        return movieID;
    }

    @Override
    public int compareTo(ViewedMovie viewedMovie) {
        return viewedMovie.count - this.count;
    }

    @Override
    public String toString() {
        return "ViewedMovie{" +
                "movieID=" + movieID +
                ", count=" + count +
                '}';
    }

    public int getMovieID() {
        return movieID;
    }

    public int getCount() {
        return count;
    }

}
