package model.primaryObjects;


import model.customerFieldEnums.EAgeRange;

import java.util.Map;

public class CustomerInfo {

    private final Map<Integer, Customer> idCustomerMap;

    public CustomerInfo(Map<Integer, Customer> idCustomerMap) {
        this.idCustomerMap = idCustomerMap;
    }

    public EAgeRange getAgeRange(int ID) {

        Customer customer = getCustomer(ID);
        EAgeRange ageRange = customer.getAgeRange();

        return ageRange;
    }

    public Customer getCustomer(int ID) {
        Customer customer = idCustomerMap.getOrDefault(ID, null);

        if (customer == null)
           throw  new IllegalArgumentException("Customer with given ID does not exist : " + ID);

        return customer;
    }

}
