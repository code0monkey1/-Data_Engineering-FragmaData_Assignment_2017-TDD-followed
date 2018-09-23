package conditions;

public final class CustomerID implements Condition {
    private final int id;
    private final int min = 1;
    private final int max = 6040;

    public CustomerID(int id) {
        this.id = id;
    }

    @Override
    public boolean isValid() {
        return id >= min && id <= max;
    }
}
