package util.FileParsing;

import conditions.Condition;
import conditions.FieldCount;
import model.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CustomerFileParser extends FileParser {


    private Map<Integer, Customer> idCustomerMap;

    public CustomerFileParser(String fileName, String parseToken, int fields) {
        super(fileName, parseToken);
        this.idCustomerMap = returnIdCustomerMap(fields);
    }

    private Map<Integer, Customer> returnIdCustomerMap(int fields) {
        Map<Integer, Customer> tempIDCustomerMap = new HashMap<>();

        List<List<String>> customerList = this.getRawList();

        // Format : UserID::Gender::Age::Occupation::Zip-code
        for (List<String> customerEntry : customerList) {

            Condition customerEntryFieldCount = new FieldCount(fields, customerEntry);

            if (!customerEntryFieldCount.isValid()) {
                throw new IllegalArgumentException("illegal field size");
            } else {
                Customer customer = returnCustomer(customerEntry);
                tempIDCustomerMap.put(customer.getId(), customer);
            }

        }
        return tempIDCustomerMap;
    }


    private Customer returnCustomer(List<String> customerEntry) {

        String id = customerEntry.get(0);
        String gender = customerEntry.get(1);
        String age = customerEntry.get(2);
        String occpation = customerEntry.get(3);
        String zip = customerEntry.get(4);

        Customer customer = new Customer(id, gender, age, occpation, zip);
        return customer;
    }

    public Map<Integer, Customer> getIdCustomerMap() {
        return idCustomerMap;
    }


}
