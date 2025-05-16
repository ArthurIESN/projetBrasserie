package BusinessLogic.Number;

public class NumberLogic
{
    private static final int MAX_INT_LENGTH = 11;
    private static final int MAX_FLOAT_LENGTH = 30;

    public int getMaxIntLength()
    {
        return MAX_INT_LENGTH;
    }

    public int getMaxFloatLength()
    {
        return MAX_FLOAT_LENGTH;
    }

    public String validateFloat(String text, boolean allowNegative, int decimalPlaces, int[] minMax)
    {
        // check if the number is too long
        if (text.length() > MAX_INT_LENGTH)
        {
            text = text.substring(0, MAX_INT_LENGTH);
        }

        // check if the number is a float
        if (!text.isEmpty() && !(allowNegative ? text.matches("-?\\d+\\.\\d+") : text.matches("\\d+\\.\\d+"))) {
            boolean hasMinus = allowNegative && text.startsWith("-");
            text = text.replaceAll("[^\\d.]", "")
                    .replaceAll("(\\d*\\.\\d*?)\\.+", "$1");
            if (text.isEmpty() || text.equals(".")) text = "0";
            if (hasMinus) text = "-" + text;
        }


        // check if the number is within the min and max range
        if (!text.isEmpty())
        {
            float number = Float.parseFloat(text);
            if (number < minMax[0])
            {
                text = String.valueOf(minMax[0]);
            }
            else if (number > minMax[1])
            {
                text = String.valueOf(minMax[1]);
            }

            // check if the number of decimal places is exceeded
            int pointIndex = text.indexOf('.');
            if (pointIndex != -1)
            {
                int decimals = text.length() - pointIndex - 1;
                if (decimals > decimalPlaces)
                {
                    text = text.substring(0, pointIndex + decimalPlaces + 1);
                }
            }
        }

        // if no decimal space but has a point, remove the point
        if (text.indexOf('.') != -1 && text.indexOf('.') == text.length() - 1)
        {
            text = text.substring(0, text.length() - 1);
        }

        return text;
    }

    public String validateInteger(String text, boolean allowNegative, int[] minMax)
    {

        // check if the number is too long
        if (text.length() > MAX_INT_LENGTH)
        {
            text = text.substring(0, MAX_INT_LENGTH);
        }

        // check if the number is an integer
        if (!text.isEmpty() && !(allowNegative ? text.matches("-?\\d+") : text.matches("\\d+"))) {
            boolean hasMinus = allowNegative && text.startsWith("-");
            text = text.replaceAll("\\D", "");
            if (text.isEmpty()) text = "0";
            if (hasMinus) text = "-" + text;
        }


        // check if the number is within the min and max range
        if (!text.isEmpty())
        {
            int number;
            try
            {
                number = Integer.parseInt(text);
            }
            catch (NumberFormatException e)
            {
                number = minMax[0];
                text = String.valueOf(number);
                System.out.println("Invalid number format: " + text + " " + e.getMessage());
            }

            if (number < minMax[0])
            {
                text = String.valueOf(minMax[0]);
            }
            else if (number > minMax[1])
            {
                text = String.valueOf(minMax[1]);
            }
        }

        // remove first 0s
        if (text.length() > 1 && text.startsWith("0"))
        {
            StringBuilder sb = new StringBuilder(text);
            while (sb.length() > 1 && sb.charAt(0) == '0')
            {
                sb.deleteCharAt(0);
            }
            text = sb.toString();
        }

        return text;
    }
}
