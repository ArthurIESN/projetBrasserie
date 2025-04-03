package UI.Components;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JDate extends JEnhancedTextField
{
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private final Date[] minMaxDates = {new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE)};
    private boolean isValidating = false;

    public JDate()
    {
        addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e)
            {
                validateDate();
            }
        });
    }

    private void removePreviousChar(String text, int caretPosition) {
        if (caretPosition > 0 && text.charAt(caretPosition - 1) == '/') {
            if (caretPosition > 1) {
                // Avant de changer le texte, gardez la position du caret
                String newText = text.substring(0, caretPosition - 2) + text.substring(caretPosition);
                setText(newText);
                setCaretPosition(caretPosition - 2);  // Positionne le caret correctement après la suppression
            } else {
                // Si le curseur est en début de texte, on enlève le "/" et on positionne le caret
                String newText = text.substring(1);
                setText(newText);
                setCaretPosition(0);  // Le caret retourne au début
            }
        } else if (caretPosition > 0) {
            // Si on n'est pas sur un "/"
            String newText = text.substring(0, caretPosition - 1) + text.substring(caretPosition);
            setText(newText);
            setCaretPosition(caretPosition - 1);  // Positionne le caret correctement après la suppression
        }
    }


    @Override
    protected void processKeyEvent(KeyEvent e) {
        char c = e.getKeyChar();
        String text = getText();
        int caretPosition = getCaretPosition();

        if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ENTER) {
            validateDate();
            return;
        }

        if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            removePreviousChar(text, caretPosition);
            return;
        }

        if (e.getID() == KeyEvent.KEY_PRESSED && (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)) {
            // Handle arrow key events separately
            super.processKeyEvent(e);
            return;
        }

        if (!Character.isDigit(c)) {
            e.consume(); // ignore the event if it's not a digit or '/'
        } else if (text.length() >= 10 && e.getID() == KeyEvent.KEY_TYPED) {
            e.consume(); // block input after 10 characters (dd/MM/yyyy)
        } else {
            super.processKeyEvent(e);
            text = getText();
            if ((text.length() == 2 || text.length() == 5) && e.getID() == KeyEvent.KEY_TYPED) {
                setText(text + "/");
            }
        }
    }

    private void validateDate()
    {
        if(isValidating) return;

        String text = getText();

        if(text.isEmpty()) return;

        if (!isDateValid(text))
        {
            isValidating = true;
            String closestValidDate = getClosestValidDate(text);
            int result = JOptionPane.showConfirmDialog(this, "Invalid date format. Please use " + DATE_FORMAT + ". Closest valid date: " + closestValidDate, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            if (result == JOptionPane.OK_OPTION)
            {
                setText(closestValidDate);
                isValidating = false;
            };

        }
    }

    public Date getDate()
    {
        String text = getText();
        if (isDateValid(text))
        {
            try {
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

    public void setDate(Date date)
    {
        if (date != null)
        {
            updateText(dateFormat.format(date));
        }
        else
        {
            updateText("");
        }
    }

    public void setMinDate(Date min)
    {
        minMaxDates[0] = min;
    }

    public void setMaxDate(Date max)
    {
        minMaxDates[1] = max;
    }

    public void setMinMaxDates(Date min, Date max)
    {
        minMaxDates[0] = min;
        minMaxDates[1] = max;
    }

    public void setMinMaxDates(Date[] minMax)
    {
        if (minMax.length == 2)
        {
            minMaxDates[0] = minMax[0];
            minMaxDates[1] = minMax[1];
        }
        else
        {
            //@todo : throw an exception
            throw new IllegalArgumentException("Min and max dates array must have exactly 2 elements.");
        }
    }

    public boolean isDateValid()
    {
        String text = getText();
        return isDateValid(text);
    }

    private boolean isDateValid(String text)
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

    private String getClosestValidDate(String text)
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
}