package util;

import jdk.dynalink.beans.StaticClass;

public class Validate {

    private Validate() {

    }

    public static boolean customerIdInRange(int id) {

        return id >= 1 && id <= 6040;
    }

    public static boolean movieIdInRange(int ID) {

        return ID >= 1 && ID <= 3952;
    }

}
