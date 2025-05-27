package Controller.Date;

import BusinessLogic.Date.DateLogic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateController
{
    private static final DateLogic dateLogic = new DateLogic();

    public static String getDateFormatString()
    {
        return dateLogic.getDateFormatString();
    }

    public static SimpleDateFormat getDateFormat()
    {
        return dateLogic.getDateFormat();
    }

    public static String getClosestValidDate(String text, Date[] minMaxDates)
    {
        return dateLogic.getClosestValidDate(text, minMaxDates);
    }

    public static boolean isDateValid(String text, Date[] minMaxDates)
    {
        return dateLogic.isDateValid(text, minMaxDates);
    }

    public static Date getDate(String text, Date[] minMaxDates)
    {
        return dateLogic.getDate(text, minMaxDates);
    }

    public static String validateDate(String text, Date[] minMaxDates)
    {
        return dateLogic.validateDate(text, minMaxDates);
    }

    public static int getMonthsBetweenDates(Date startDate, Date endDate)
    {
        return dateLogic.getMonthsBetweenDates(startDate, endDate);
    }

    public static String getDateString(Date date)
    {
        return dateLogic.getDateString(date);
    }

    public static String getShowDateString(Date date)
    {
        return dateLogic.getShowDateString(date);
    }

}
