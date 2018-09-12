package util.FileParsing;

import model.Customer;
import model.customerFieldEnums.EAgeRange;
import model.customerFieldEnums.EGender;
import model.customerFieldEnums.EOccupation;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CustomerFileParserTest {
    Map<Integer, Customer> idCustomerMap;
    private CustomerFileParser customerFileParser;

    @Before
    public void setUp() throws Exception {
        this.customerFileParser = new CustomerFileParser("C:\\Users\\Chiranjeev\\Desktop\\MyCode\\Competitive\\Fragma  Data 2017 movies pre interview assignment ( Entry Level Java Developer Role ) TDD\\src\\mockObjects\\mockCustomers.dat", "::", 5);
        this.idCustomerMap = customerFileParser.getIdCustomerMap();
    }

    @Test
    public void customer_genderIsValid() {


        assertEquals(EGender.FEMALE, idCustomerMap.get(1).getGender());
        assertEquals(EGender.MALE, idCustomerMap.get(9).getGender());
    }

    @Test
    public void customer_ageRangeIsValid() {

        assertEquals(EAgeRange.UNDER_EIGHTEEN, idCustomerMap.get(1).getAgeRange());
        assertEquals(EAgeRange.TWENTY_FIVE_TO_THIRTY_FOUR, idCustomerMap.get(9).getAgeRange());

    }

    @Test
    public void customer_occupationIsValid() {
        assertEquals(EOccupation.K_TWELVE_STUDENT, idCustomerMap.get(1).getOccupation());
        assertEquals(EOccupation.TECHNICIAN_ENGINEER, idCustomerMap.get(9).getOccupation());

    }

    @Test
    public void customer_zipValid() {
        assertEquals("48067", idCustomerMap.get(1).getZipCode());
    }

    @Test
    public void customer_mapSizeIs10() {
         assertEquals(10,idCustomerMap.size());
    }

}