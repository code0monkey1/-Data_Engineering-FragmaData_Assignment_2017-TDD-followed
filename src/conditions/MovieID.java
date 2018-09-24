package conditions;

public final class MovieID implements Condition {

    private final int id;
    private final int MIN = 1;
    private final int MAX = 3952;

    public MovieID(int id) {
        this.id = id;
    }

    @Override
    public boolean isValid() {
        return id >= MIN && id <= MAX;
    }


}
