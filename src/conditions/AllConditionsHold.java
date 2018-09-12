package conditions;

public class AllConditionsHold implements Condition {

    private Condition[] conditions;

    public AllConditionsHold(Condition... conditions) {
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
