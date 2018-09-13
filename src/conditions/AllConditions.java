package conditions;

public class AllConditions implements Condition {

    private Condition[] conditions;

    public AllConditions(Condition... conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean isValid() {

        for (Condition condition : conditions) {
            if (!condition.isValid()) return false;
        }
        return true;
    }
}
