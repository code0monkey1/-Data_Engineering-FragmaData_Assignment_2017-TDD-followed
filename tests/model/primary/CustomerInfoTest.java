package model.primary;

import model.primary.customer.Customer;
import model.primary.customer.CustomerInfo;
import model.primary.customer.EAgeRange;
import org.junit.Before;
import org.junit.Test;
import util.FileParsing.FileParser;
import util.mapping.CustomerMapper;

import static org.junit.Assert.assertEquals;

public class CustomerInfoTest {
    private CustomerInfo customerInfo;


    @Before
    public void setUp() {
        CustomerMapper customerMapper = new CustomerMapper(new FileParser("users.dat", "::"), 5);
        customerInfo = new CustomerInfo(customerMapper.getIdCustomerMap());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAgeRange_idInvalid() {
        customerInfo.getAgeRange(6041);

        customerInfo.getAgeRange(0);
    }

    @Test
    public void getAgeRange_correctIdGivesCorrectRange() {
        EAgeRange expected = EAgeRange.UNDER_EIGHTEEN;
        EAgeRange ageRange = customerInfo.getAgeRange(1);

        assertEquals(expected, ageRange);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCustomer_idInvalid() {
        Customer customer = customerInfo.getCustomer(0);
        Customer customer1 = customerInfo.getCustomer(6041);
    }
}