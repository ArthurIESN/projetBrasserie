package BusinessLogic.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateLogic
{
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String DATE_SHOW_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final SimpleDateFormat dateShowFormat = new SimpleDateFormat(DATE_SHOW_FORMAT);

    public String getDateFormatString()
    {
        return DATE_FORMAT;
    }

    public SimpleDateFormat getDateFormat()
    {
        return dateFormat;
    }

    public String getClosestValidDate(String text, Date[] minMaxDates)
    {
        Calendar calendar = Calendar.getInstance();

        if(text == null) text = "" ;

        try
        {
            calendar.setTime(dateFormat.parse(text));
        }
        catch (ParseException e)
        {
            // If parsing fails, adjust the date parts

            String[] parts = text.split("/");

            try
            {
                int day = parts.length > 0 && !parts[0].isEmpty() ? Integer.parseInt(parts[0]) : 1;
                int month = parts.length > 1 && !parts[1].isEmpty()? Integer.parseInt(parts[1]) - 1 : 0; // Calendar months are 0-based
                int year = parts.length > 2 && !parts[2].isEmpty()? Integer.parseInt(parts[2]) : calendar.get(Calendar.YEAR);

                calendar.set(Calendar.DAY_OF_MONTH, Math.min(day, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)));
                calendar.set(Calendar.MONTH, Math.min(month, 11));
                calendar.set(Calendar.YEAR, year);
            }
            catch (NumberFormatException ex)
            {
                // If parsing fails, set to default values
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.MONTH, 0);
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
            }
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

    public boolean isDateValid(String text, Date[] minMaxDates)
    {
        if (text == null || !text.matches("\\d{2}/\\d{2}/\\d{4}"))
        {
            return false;
        }

        dateFormat.setLenient(false);
        try
        {
            Date date = dateFormat.parse(text);

            return (date.equals(minMaxDates[0]) || date.equals(minMaxDates[1]) || (!date.before(minMaxDates[0]) && !date.after(minMaxDates[1])));
        }
        catch (ParseException e)
        {
            return false;
        }
    }

    public Date getDate(String text, Date[] minMaxDates)
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

    public String validateDate(String text, Date[] minMaxDates)
    {
        if(text.isEmpty()) return "";
        if(isDateValid(text, minMaxDates)) return text;

        return getClosestValidDate(text, minMaxDates);

    }

    public int getMonthsBetweenDates(Date startDate, Date endDate)
    {
        LocalDate startDateLocal = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDateLocal = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return (int) ChronoUnit.MONTHS.between(endDateLocal, startDateLocal);
    }

    public String getDateString(Date date)
    {
        return dateFormat.format(date);
    }

    public String getShowDateString(Date date)
    {
        return dateShowFormat.format(date);
    }
}
