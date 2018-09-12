package conditions;

public class CustomerIdValid implements Condition {
    private int id;

    public CustomerIdValid(int id) {
        this.id = id;
    }

    @Override
    public boolean valid() {
        return id >= 1 && id <= 6040;
    }
}
