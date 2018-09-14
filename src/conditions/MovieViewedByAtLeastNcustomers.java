package conditions;

import helperObjects.MovieRatingCount;

public class MovieViewedByAtLeastNcustomers implements Condition {

    private MovieRatingCount movieRatingCount;
    private int n;

    public MovieViewedByAtLeastNcustomers(MovieRatingCount movieRatingCount,
                                          int n) {
        this.n = n;
        this.movieRatingCount = movieRatingCount;
    }

    @Override
    public boolean isValid() {
        return movieRatingCount.getRating() >= n;
    }
}
