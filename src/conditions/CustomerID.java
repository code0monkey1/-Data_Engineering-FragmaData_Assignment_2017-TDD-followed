package conditions;

public class CustomerID implements Condition {
    private int id;
    private final int min = 1;
    private final int max = 6040;

    public CustomerID(int id) {
        this.id = id;
    }

    @Override
    public boolean valid() {
        return id >= min && id <= max;
    }
}
