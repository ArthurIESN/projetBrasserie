package BusinessLogic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateLogic
{
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public static String getDateFormatString()
    {
        return DATE_FORMAT;
    }

    public static SimpleDateFormat getDateFormat()
    {
        return dateFormat;
    }

    public static String getClosestValidDate(String text, Date[] minMaxDates)
    {
        Calendar calendar = Calendar.getInstance();
        try
        {
            calendar.setTime(dateFormat.parse(text));
        }
        catch (ParseException e)
        {
            // If parsing fails, adjust the date parts
            String[] parts = text.split("/");
            int day = parts.length > 0 ? Integer.parseInt(parts[0]) : 1;
            int month = parts.length > 1 ? Integer.parseInt(parts[1]) - 1 : 0; // Calendar months are 0-based
            int year = parts.length > 2 ? Integer.parseInt(parts[2]) : calendar.get(Calendar.YEAR);

            calendar.set(Calendar.DAY_OF_MONTH, Math.min(day, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
            calendar.set(Calendar.MONTH, Math.min(month, 11));
            calendar.set(Calendar.YEAR, year);
        }

        // check for min max dates
        if (calendar.getTime().after(minMaxDates[1]))
        {
            calendar.setTime(minMaxDates[1]);
        } else if (calendar.getTime().before(minMaxDates[0]))
        {
            calendar.setTime(minMaxDates[0]);
        }

        return dateFormat.format(calendar.getTime());
    }

    public static boolean isDateValid(String text, Date[] minMaxDates)
    {
        if (text == null || !text.matches("\\d{2}/\\d{2}/\\d{4}"))
        {
            return false;
        }

        dateFormat.setLenient(false);
        try
        {
            Date date = dateFormat.parse(text);

            return !date.before(minMaxDates[0]) && !date.after(minMaxDates[1]);
        }
        catch (ParseException e)
        {
            return false;
        }
    }

    public static Date getDate(String text, Date[] minMaxDates)
    {
        if (isDateValid(text, minMaxDates))
        {
            try
            {
                return dateFormat.parse(text);
            } catch (ParseException e)
            {
                System.out.println("Error parsing date: " + e.getMessage() + "\n" +
                        "Date: " + text + "\n" +
                        "Expected format: " + DATE_FORMAT);
            }
        }
        return null;
    }

    public static String validateDate(String text, Date[] minMaxDates)
    {
        if(text.isEmpty()) return "";
        if(isDateValid(text, minMaxDates)) return text;

        return getClosestValidDate(text, minMaxDates);

    }
}
