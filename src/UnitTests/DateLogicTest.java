package UnitTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import BusinessLogic.Date.DateLogic;

import static org.junit.jupiter.api.Assertions.*;

class DateLogicTest {

    private static DateLogic dateLogic;
    private static SimpleDateFormat dateFormat;
    private static Date[] minMaxDates;

    @BeforeAll
    static void setUp() {
        dateLogic = new DateLogic();

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            minMaxDates = new Date[]{
                    dateFormat.parse("01/01/2020"),
                    dateFormat.parse("31/12/2023")
            };
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date: " + e.getMessage(), e);
        }
    }

    @Test
    void isDateValid() {
        assertTrue(dateLogic.isDateValid("15/06/2021", minMaxDates));

        assertTrue(dateLogic.isDateValid("01/01/2020", minMaxDates));

        assertTrue(dateLogic.isDateValid("31/12/2023", minMaxDates));

        assertFalse(dateLogic.isDateValid("31/12/2019", minMaxDates));

        assertFalse(dateLogic.isDateValid("01/01/2024", minMaxDates));

        assertFalse(dateLogic.isDateValid("2021-06-15", minMaxDates));

        assertFalse(dateLogic.isDateValid(null, minMaxDates));
    }

    @Test
    void testgetMonthsBetweenDates() throws ParseException{
        Date startDate;
        Date endDate;

        startDate = dateFormat.parse("15/06/2021");
        endDate = dateFormat.parse("20/06/2021");
        assertEquals(0,dateLogic.getMonthsBetweenDates(startDate,endDate));

        startDate = dateFormat.parse("15/06/2021");
        endDate = dateFormat.parse("20/08/2021");
        assertEquals(2,dateLogic.getMonthsBetweenDates(startDate,endDate));

        startDate = dateFormat.parse("01/01/2021");
        endDate = dateFormat.parse("01/01/2022");
        assertEquals(12,dateLogic.getMonthsBetweenDates(startDate,endDate));

        startDate = dateFormat.parse("01/01/2021");
        endDate = dateFormat.parse("01/01/2023");
        assertEquals(24,dateLogic.getMonthsBetweenDates(startDate,endDate));

        startDate = dateFormat.parse("01/06/2021");
        endDate = dateFormat.parse("01/01/2021");

        assertEquals(5,dateLogic.getMonthsBetweenDates(startDate,endDate));
    }

    @Test
    void testGetDateWithValidDate() throws ParseException{
        String validDate = "15/06/2021";
        Date expectedDate = dateFormat.parse(validDate);

        assertNotNull(dateLogic.getDate(validDate,minMaxDates));
        assertEquals(expectedDate,dateLogic.getDate(validDate,minMaxDates));

        String invalideDate = "32/12/2025";
        assertNull(dateLogic.getDate(invalideDate,minMaxDates));

        String outOfBoundsDate = "01/01/2019";
        assertNull(dateLogic.getDate(outOfBoundsDate,minMaxDates));

        String nullInput = null;
        assertNull(dateLogic.getDate(nullInput,minMaxDates));

        String emptyInput = "";
        assertNull(dateLogic.getDate(emptyInput,minMaxDates));
    }

    @Test
    void testGetClosesValidDate() throws ParseException{
        String validDate = "15/06/2021";

        assertEquals(validDate,dateLogic.getClosestValidDate(validDate,minMaxDates));

        String invalidDate = "32/12/2025";
        assertEquals(dateFormat.format(minMaxDates[1]),dateLogic.getClosestValidDate(invalidDate,minMaxDates));

        String outOfBoundsDate = "01/01/2019";
        assertEquals(dateFormat.format(minMaxDates[0]),dateLogic.getClosestValidDate(outOfBoundsDate,minMaxDates));

        String nullInput = null;
        assertEquals("31/12/2023",dateLogic.getClosestValidDate(nullInput,minMaxDates));

        String emptyInput = "";
        assertEquals("31/12/2023",dateLogic.getClosestValidDate(emptyInput,minMaxDates));

        String test = "21/Test/2020";
        assertEquals("31/12/2023",dateLogic.getClosestValidDate(test,minMaxDates));
    }
}