package UI.Components.Fields;

import Controller.Date.DateController;
import Exceptions.Date.MinMaxDatesException;

import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Date;


public class JDateField extends JEnhancedTextField
{

    private final Date[] minMaxDates = {new Date(Long.MIN_VALUE), new Date(Long.MAX_VALUE)};

    public JDateField()
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

    private void removePreviousChar(String text, int caretPosition)
    {

        if(text.length() <= caretPosition) return;

        if (caretPosition > 0 && text.charAt(caretPosition - 1) == '/')
        {
            if (caretPosition > 1)
            {
                String newText = text.substring(0, caretPosition - 2) + text.substring(caretPosition);
                setText(newText);
                setCaretPosition(caretPosition - 2);
            } else
            {
                String newText = text.substring(1);
                setText(newText);
                setCaretPosition(0);
            }
        }
        else if (caretPosition > 0)
        {
            String newText = text.substring(0, caretPosition - 1) + text.substring(caretPosition);
            setText(newText);
            setCaretPosition(caretPosition - 1);
        }
    }


    @Override
    protected void processKeyEvent(KeyEvent e) {
        char c = e.getKeyChar();
        String text = getText();
        int caretPosition = getCaretPosition();

        if (e.getID() != KeyEvent.KEY_TYPED)
        {
            super.processKeyEvent(e);
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
            e.consume();
        } else if (text.length() >= 10 && e.getID() == KeyEvent.KEY_TYPED) {
            e.consume();
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
        updateText(DateController.validateDate(getText(), minMaxDates));
    }

    public Date getDate()
    {
        return DateController.getDate(getText(), minMaxDates);
    }
    public void setDate(Date date)
    {
        if (date != null)
        {
            updateText(DateController.getDateFormat().format(date));
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

    public void setMinMaxDates(Date[] minMax) throws MinMaxDatesException
    {
        if (minMax.length == 2)
        {
            minMaxDates[0] = minMax[0];
            minMaxDates[1] = minMax[1];
        }
        else
        {
            throw new MinMaxDatesException("Min and max dates array must have exactly 2 elements.");
        }
    }

    public boolean isDateValid()
    {
        String text = getText();
        return DateController.isDateValid(text, minMaxDates);
    }

}