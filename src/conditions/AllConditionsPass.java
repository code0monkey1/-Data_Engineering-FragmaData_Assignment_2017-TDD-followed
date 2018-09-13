package conditions;

public class AllConditionsPass implements Condition {

    private Condition[] conditions;

    public AllConditionsPass(Condition... conditions) {
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
