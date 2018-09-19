package conditions;

public class CountAtLeast implements Condition {

    private int viewCount;
    private int expectedViewCount;

    public CountAtLeast(int expectedViewCount,
                        int viewCount) {

        this.viewCount = viewCount;
        this.expectedViewCount = expectedViewCount;
    }

    @Override
    public boolean isValid() {
        return viewCount >= expectedViewCount;
    }
}
