package conditions;

public final class MovieID implements Condition {

    private final int id;
    private final int min = 1;
    private final int max = 3952;

    public MovieID(int id) {
        this.id = id;
    }

    @Override
    public boolean isValid() {
        return id >= min && id <= max;
    }


}
