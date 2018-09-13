package conditions;

import java.util.List;

public class FieldCount implements Condition {
    private final List<String> list;
    private final int fields;

    @Override
    public boolean isValid() {
        return fields == getListSize();
    }

    public FieldCount(int fields, List<String> list) {
        this.list = list;
        this.fields = fields;
    }

    public int getListSize() {
        return list.size();
    }
}
