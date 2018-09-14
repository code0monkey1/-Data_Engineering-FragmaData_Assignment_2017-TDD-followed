package model.primaryObjects;


import java.util.Map;

public class CustomerInfo {

    private final Map<Integer, Customer> idCustomerMap;

    public CustomerInfo(Map<Integer, Customer> idCustomerMap) {
        this.idCustomerMap = idCustomerMap;
    }


}
