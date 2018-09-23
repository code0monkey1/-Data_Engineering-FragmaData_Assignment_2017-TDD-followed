package util.FileParsing;

import model.primary.customer.Customer;
import model.primary.customer.AgeRange;
import model.primary.customer.Gender;
import model.primary.customer.Occupation;
import org.junit.Before;
import org.junit.Test;
import util.mapping.CustomerMapper;

import java.util.Map;

import static org.junit.Assert.assertEquals;


public class CustomerMapperTest {

    private CustomerMapper customerMapper;

    @Before
    public void setUp() {
        FileParser fileParser = new FileParser("mockCustomers.dat", "::");
        this.customerMapper = new CustomerMapper(fileParser, 5);
    }

    @Test
    public void customer_genderIsValid() {
        Map<Integer, Customer> idCustomerMap = customerMapper.getIdCustomerMap();

        assertEquals(Gender.FEMALE, idCustomerMap.get(1).getGender());
        assertEquals(Gender.MALE, idCustomerMap.get(9).getGender());
    }

    @Test
    public void customer_ageRangeIsValid() {
        Map<Integer, Customer> idCustomerMap = customerMapper.getIdCustomerMap();

        assertEquals(AgeRange.UNDER_EIGHTEEN, idCustomerMap.get(1).getAgeRange());
        assertEquals(AgeRange.TWENTY_FIVE_TO_THIRTY_FOUR, idCustomerMap.get(9).getAgeRange());

    }

    @Test
    public void customer_occupationIsValid() {
        Map<Integer, Customer> idCustomerMap = customerMapper.getIdCustomerMap();

        assertEquals(Occupation.K_TWELVE_STUDENT, idCustomerMap.get(1).getOccupation());
        assertEquals(Occupation.TECHNICIAN_ENGINEER, idCustomerMap.get(9).getOccupation());

    }

    @Test
    public void customer_zipValid() {
        Map<Integer, Customer> idCustomerMap = customerMapper.getIdCustomerMap();
        assertEquals("48067", idCustomerMap.get(1).getZipCode());
    }

    @Test
    public void customer_mapSizeIs10() {
        Map<Integer, Customer> idCustomerMap = customerMapper.getIdCustomerMap();
        assertEquals(10, idCustomerMap.size());
    }

}