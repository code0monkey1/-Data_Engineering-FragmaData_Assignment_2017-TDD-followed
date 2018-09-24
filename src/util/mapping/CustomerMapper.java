package util.mapping;

import conditions.Condition;
import conditions.FieldCount;
import model.primary.customer.Customer;
import util.FileParsing.FileParser;
import wrappers.CustomerMap;

import java.util.List;
import java.util.Map;

public final class CustomerMapper {


    private final CustomerMap idCustomerMap;

    public CustomerMapper(FileParser fileParser, int fields) {
        this.idCustomerMap = customerMap(fileParser,fields);
    }

    private CustomerMap customerMap(FileParser fileParser, int fields) {
        CustomerMap tempIDCustomerMap = new CustomerMap();

        List<List<String>> customerList = fileParser.getRawList();

        // Format : UserID::Gender::Age::Occupation::Zip-code
        for (List<String> customerEntry : customerList) {

            Condition customerEntryFieldCount = new FieldCount(fields, customerEntry);

            if (!customerEntryFieldCount.isValid())
                throw new IllegalArgumentException("illegal field size");
             else {
                Customer customer = customer(customerEntry);
                tempIDCustomerMap.put(customer.getId(), customer);
            }

        }
        return tempIDCustomerMap;
    }


    private Customer customer(List<String> customerEntry) {

        String id = customerEntry.get(0);
        String gender = customerEntry.get(1);
        String age = customerEntry.get(2);
        String occupation = customerEntry.get(3);
        String zip = customerEntry.get(4);

        Customer customer = new Customer(id, gender, age, occupation, zip);
        return customer;
    }

    public Map<Integer, Customer> getIdCustomerMap() {
        return idCustomerMap;
    }


}
