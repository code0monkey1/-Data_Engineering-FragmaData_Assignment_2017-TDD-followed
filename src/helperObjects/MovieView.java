package helperObjects;

public class MovieView implements Comparable<MovieView> {


    private int movieID;
    private int count;

    public MovieView(int movieID, int count) {
        this.movieID = movieID;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieView movieView = (MovieView) o;

        if (movieID != movieView.movieID) return false;
        return count == movieView.count;
    }

    @Override
    public int hashCode() {
        int result = movieID;
        result = 31 * result + count;
        return result;
    }

    @Override
    public int compareTo(MovieView movieView) {
        return movieView.count - this.count;
    }

    @Override
    public String toString() {
        return "MovieView{" +
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
