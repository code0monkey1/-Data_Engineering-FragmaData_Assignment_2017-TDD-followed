package conditions;

public class AllConditions implements Condition {

    private Condition[] conditions;

    public AllConditions(Condition... conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean valid() {

        for (Condition condition : conditions) {
            if (!condition.valid()) return false;
        }
        return true;
    }
}
