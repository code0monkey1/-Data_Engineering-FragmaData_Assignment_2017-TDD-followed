package conditions;

public class CountAtLeast implements Condition {

    private int count;
    private int expectedCount;

    public CountAtLeast(int expectedCount,
                        int count) {

        this.count = count;
        this.expectedCount = expectedCount;
    }

    @Override
    public boolean isValid() {
        return count >= expectedCount;
    }
}
