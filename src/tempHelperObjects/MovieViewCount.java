package tempHelperObjects;

public class MovieViewCount implements Comparable<MovieViewCount> {
    public int getMovieID() {
        return movieID;
    }

    public int getCount() {
        return count;
    }

    private int movieID;
    private int count;

    public MovieViewCount(int movieID, int count) {
        this.movieID = movieID;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieViewCount that = (MovieViewCount) o;

        return movieID == that.movieID;
    }

    @Override
    public int hashCode() {
        return movieID;
    }

    @Override
    public int compareTo(MovieViewCount movieViewCount) {
        return movieViewCount.count - this.count;
    }

    @Override
    public String toString() {
        return "MovieViewCount{" +
                "movieID=" + movieID +
                ", count=" + count +
                '}';
    }
}
