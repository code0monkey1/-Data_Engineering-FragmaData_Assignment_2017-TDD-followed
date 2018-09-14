package util.FileParsing;

import model.Customer;
import model.customerFieldEnums.EAgeRange;
import model.customerFieldEnums.EGender;
import model.customerFieldEnums.EOccupation;
import org.junit.Before;
import org.junit.Test;
import util.mapping.CustomerMapper;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest {

    private CustomerMapper customerMapper;

    @Before
    public void setUp() throws Exception {
        FileParser fileParser = new FileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockCustomers.dat", "::");
        this.customerMapper = new CustomerMapper(fileParser, 5);
    }

    @Test
    public void customer_genderIsValid() {
        Map<Integer, Customer> idCustomerMap=customerMapper.getIdCustomerMap();

        assertEquals(EGender.FEMALE, idCustomerMap.get(1).getGender());
        assertEquals(EGender.MALE, idCustomerMap.get(9).getGender());
    }

    @Test
    public void customer_ageRangeIsValid() {
        Map<Integer, Customer> idCustomerMap=customerMapper.getIdCustomerMap();

        assertEquals(EAgeRange.UNDER_EIGHTEEN, idCustomerMap.get(1).getAgeRange());
        assertEquals(EAgeRange.TWENTY_FIVE_TO_THIRTY_FOUR, idCustomerMap.get(9).getAgeRange());

    }

    @Test
    public void customer_occupationIsValid() {
        Map<Integer, Customer> idCustomerMap=customerMapper.getIdCustomerMap();

        assertEquals(EOccupation.K_TWELVE_STUDENT, idCustomerMap.get(1).getOccupation());
        assertEquals(EOccupation.TECHNICIAN_ENGINEER, idCustomerMap.get(9).getOccupation());

    }

    @Test
    public void customer_zipValid() {
        Map<Integer, Customer> idCustomerMap=customerMapper.getIdCustomerMap();
        assertEquals("48067", idCustomerMap.get(1).getZipCode());
    }

    @Test
    public void customer_mapSizeIs10() {
        Map<Integer, Customer> idCustomerMap=customerMapper.getIdCustomerMap();
        assertEquals(10, idCustomerMap.size());
    }

}