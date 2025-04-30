package Controller.Number;

import BusinessLogic.Number.NumberLogic;

public class NumberController
{
    private static final NumberLogic numberLogic = new NumberLogic();

    public static int getMaxIntLength()
    {
        return numberLogic.getMaxIntLength();
    }

    public static int getMaxFloatLength()
    {
        return numberLogic.getMaxFloatLength();
    }

    public static String validateFloat(String text, boolean allowNegative, int decimalPlaces, int[] minMax)
    {
        return numberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax);
    }

    public static String validateInteger(String text, boolean allowNegative, int[] minMax)
    {
        return numberLogic.validateInteger(text, allowNegative, minMax);
    }
}
