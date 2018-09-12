package util.FileParsing;

import model.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CustomerFileParser extends FileParser {


    private Map<Integer, Customer> idCustomerMap;
    private int fields;

    public CustomerFileParser(String fileName, String parseToken, int fields) {
        super(fileName, parseToken);
        this.fields = fields;
        this.idCustomerMap = returnIdCustomerMap();
    }

    private Map<Integer, Customer> returnIdCustomerMap() {
        Map<Integer, Customer> tempIDCustomerMap = new HashMap<>();

        List<List<String>> customerList = this.getRawEntriesList();

        // Format : UserID::Gender::Age::Occupation::Zip-code
        for (List<String> customerEntry : customerList) {

            if (customerEntry.size() != fields) {
                throw new IllegalArgumentException("illegal field size");
            }
            Customer customer = returnCustomer(customerEntry);
            tempIDCustomerMap.put(customer.getId(), customer);

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
