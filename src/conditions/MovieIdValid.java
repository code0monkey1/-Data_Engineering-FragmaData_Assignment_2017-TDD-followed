package conditions;

public class MovieIdValid implements Condition {

    private int id;

    public MovieIdValid(int id) {
        this.id = id;
    }

    @Override
    public boolean valid() {
        return id >= 1 && id <= 3952;
    }
}
