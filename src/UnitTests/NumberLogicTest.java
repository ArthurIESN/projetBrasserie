package UnitTests;

import BusinessLogic.Number.NumberLogic;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

//@todo : check

class NumberLogicTest {


    private NumberLogic numberLogic = new NumberLogic();

    @BeforeEach
    void setUp()
    {
    }

    @Test
    void validateFloat()
    {
        int[] minMax = {0, 100};
        int decimalPlaces = 2;
        boolean allowNegative = false;
        String text;

        text = "";
        assertEquals("", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "123.45999";
        assertEquals("100", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "99.999999";
        assertEquals("99.99", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "100";
        assertEquals("100", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "0";
        assertEquals("0", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "-1";
        assertEquals("1", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "-99.99";
        assertEquals("99.99", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "50.1234";
        assertEquals("50.12", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "75.5";
        assertEquals("75.5", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "99.999";
        assertEquals("99.99", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "99.994";
        assertEquals("99.99", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "100.0001";
        assertEquals("100", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "abc";
        assertEquals("0", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = " ";
        assertEquals("0", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "0.001";
        assertEquals("0.00", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "1.999";
        assertEquals("1.99", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "99.9949";
        assertEquals("99.99", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "-0.01";
        assertEquals("0.01", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        allowNegative = true;

        text = "-0.01";
        assertEquals("0", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "-100";
        assertEquals("0", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "-100.001";
        assertEquals("0", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "-0.001";
        assertEquals("0", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        decimalPlaces = 0;

        text = "99.9";
        assertEquals("99", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "50.99";
        assertEquals("50", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "-50.9";
        assertEquals("0", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        decimalPlaces = 3;

        text = "12.9999";
        assertEquals("12.999", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "-12.98765";
        assertEquals("0", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        minMax = new int[]{10, 20};

        text = "25.5";
        assertEquals("20", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "5.12345";
        assertEquals("10", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));

        text = "15.6789";
        assertEquals("15.678", numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));
    }

    @Test
    void validateInteger() {
        int[] minMax = {0, 100};
        boolean allowNegative = false;
        String text;

        text = "";
        assertEquals("", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "50";
        assertEquals("50", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "100";
        assertEquals("100", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "101";
        assertEquals("100", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "0";
        assertEquals("0", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "-1";
        assertEquals("1", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "-100";
        assertEquals("100", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "999";
        assertEquals("100", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "0012";
        assertEquals("12", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "   23";
        assertEquals("23", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "12 ";
        assertEquals("12", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "abc";
        assertEquals("0", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "12abc";
        assertEquals("12", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "12.99";
        assertEquals("100", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "99.9";
        assertEquals("100", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "-99.9";
        assertEquals("100", numberLogic.validateInteger(text, allowNegative, minMax));

        allowNegative = true;

        text = "-50";
        assertEquals("0", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "-101";
        assertEquals("0", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "-0";
        assertEquals("-0", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "-1";
        assertEquals("0", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "-100";
        assertEquals("0", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "1000";
        assertEquals("100", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "0.99";
        assertEquals("99", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "99.99";
        assertEquals("100", numberLogic.validateInteger(text, allowNegative, minMax));

        minMax = new int[]{10, 20};

        text = "5";
        assertEquals("10", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "25";
        assertEquals("20", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "15";
        assertEquals("15", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "-15";
        assertEquals("10", numberLogic.validateInteger(text, allowNegative, minMax));

        minMax = new int[]{-50, 50};

        text = "-60";
        assertEquals("-50", numberLogic.validateInteger(text, allowNegative, minMax));

        text = "60";
        assertEquals("50", numberLogic.validateInteger(text, allowNegative, minMax));
    }

}