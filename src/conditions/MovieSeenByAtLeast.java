package conditions;

public class MovieSeenByAtLeast implements Condition {

    private int viewCount;
    private int expectedViewCount;

    public MovieSeenByAtLeast(int expectedViewCount,
                              int viewCount) {

        this.viewCount = viewCount;
        this.expectedViewCount = expectedViewCount;
    }

    @Override
    public boolean isValid() {
        return viewCount >= expectedViewCount;
    }
}
