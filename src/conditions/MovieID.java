package conditions;

public class MovieID implements Condition {

    private int id;
    private final int min = 1;
    private final int max = 3951;

    public MovieID(int id) {
        this.id = id;
    }

    @Override
    public boolean isValid() {
        return id >= min && id <= max;
    }


}
