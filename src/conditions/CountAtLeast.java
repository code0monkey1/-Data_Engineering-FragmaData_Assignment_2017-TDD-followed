package conditions;

public final class CountAtLeast implements Condition {

    private final  int count;
    private final int expectedCount;

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
