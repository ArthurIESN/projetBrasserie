package UI.Components.Fields;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class JNumberField extends JEnhancedTextField
{
    /**
     * Type of number to be entered in the field (INTEGER or FLOAT).
     */
    public enum NumberType
    {
        INTEGER,
        FLOAT
    }

    private final NumberType numberType;
    private final int decimalPlaces;
    private final int[] minMax = {Integer.MIN_VALUE, Integer.MAX_VALUE};
    private boolean allowNegative = true;
    private static final int MAX_INT_LENGTH = 11;
    private static final int MAX_FLOAT_LENGTH = 30;

    /**
     * Constructor for JNumberField with specified number type and decimal places.
     * @param numberType the type of number (INTEGER or FLOAT)
     * @param decimalPlaces the number of decimal places for FLOAT type
     */
    public JNumberField(NumberType numberType, int decimalPlaces)
    {
        this.numberType = numberType;
        this.decimalPlaces = decimalPlaces;

        addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e) {}

            @Override
            public void focusLost(FocusEvent e)
            {
                validateNumber(getText());
            }
        });
    }

    /**
     * Constructor for JNumberField with default decimal places.
     * It creates a field that accepts NumberType and has no decimal places.
     * @param numberType the type of number (INTEGER or FLOAT)
     */
    public JNumberField(NumberType numberType)
    {
        this(numberType, 0);
    }

    /**
     * Constructor for JNumberField with default values.
     * It creates a field that accepts integers and has no decimal places.
     */
    public JNumberField()
    {
        this(NumberType.INTEGER, 0);
    }

    /**
     * Set the minimum and maximum values for the field.
     * @param min the minimum value
     * @param max the maximum value
     */
    public void setMinMax(int min, int max)
    {
        this.minMax[0] = min;
        this.minMax[1] = max;
    }

    /*
     * Set if the field can accept negative numbers.
     * @param allowNegative true if the field can accept negative numbers, false otherwise
     */
    public void setAllowNegative(boolean allowNegative)
    {
        this.allowNegative = allowNegative;
    }

    /**
     * Get the integer value from the field.
     * @return the integer value
     */
    public int getInt()
    {
        validateNumber(getText());
        String text = getText();
        if (text.isEmpty())
        {
            return 0;
        }
        return Integer.parseInt(text);
    }

    /**
     * Get the float value from the field.
     * @return the float value
     */
    public float getFloat()
    {
        validateNumber(getText());
        String text = getText();
        if (text.isEmpty())
        {
            return 0;
        }
        return Float.parseFloat(text);
    }


    @Override
    protected void processKeyEvent(KeyEvent e)
    {
        if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ENTER) {
            validateNumber(getText());
            return;
        }

        if (e.getID() != KeyEvent.KEY_TYPED)
        {
            super.processKeyEvent(e);
            return;
        }

        char c = e.getKeyChar();
        String text = getText();

        switch (numberType)
        {
            case INTEGER:

                if(text.length() >= MAX_INT_LENGTH)
                {
                    e.consume();
                    return;
                }

                if(canWriteNegativeSign(c, text))
                {
                    break;
                }

                if (!Character.isDigit(c))
                {
                    e.consume();
                    return;
                }
                break;

            case FLOAT:


                if(text.length() >= MAX_FLOAT_LENGTH)
                {
                    e.consume();
                    return;
                }

                if(canWriteNegativeSign(c, text))
                {
                    break;
                }

                if (!Character.isDigit(c) && c != '.')
                {
                    e.consume();
                    return;
                }

                // We only accept one decimal point
                if (c == '.' && text.contains("."))
                {
                    e.consume();
                    return;
                }

                // Check if the number of decimal places is exceeded
                int pointIndex = text.indexOf('.');
                if (pointIndex != -1 && Character.isDigit(c))
                {
                    int decimals = text.length() - pointIndex - 1;

                    // If the number of decimals is exceeded, consume the event
                    int caretPos = getCaretPosition();
                    if (caretPos > pointIndex && decimals >= decimalPlaces)
                    {
                        e.consume();
                        return;
                    }
                }

                break;
        }

        // process the key event if all checks are passed
        super.processKeyEvent(e);
    }

    @Override
    public void paste()
    {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        try
        {
            String clipboardText = (String) clipboard.getData(DataFlavor.stringFlavor);
            if (clipboardText == null) return;

            clipboardText = getText() + clipboardText;

            validateNumber(clipboardText);

        }
        catch (IOException | UnsupportedFlavorException e)
        {
            JOptionPane.showMessageDialog(this,
                    "Error occurred while pasting text",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
        /**
     * Validate the number in the field based on the type (INTEGER or FLOAT).
     * It checks if the number is within the min and max range and if it has
     * the correct number of decimal places. Also check if the number can be negative.
     */
    private void validateNumber(String text)
    {
        switch (numberType)
        {
            case INTEGER:

                updateText(validateInteger(text));
                break;

            case FLOAT:

                updateText(validateFloat(text));
                break;
        }
    }

    private String validateInteger(String text)
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

        return text;
    }

    private String validateFloat(String text)
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

        return text;
    }



    /**
     *  * Check if the field can accept a negative sign.
     * @param c the character to check (negative sign)
     * @param text the current text in the field
     * @return true if the field can accept a negative sign, false otherwise
     */
    private boolean canWriteNegativeSign(char c, String text)
    {
        return c == '-' && allowNegative && !text.contains("-") && getCaretPosition() == 0;
    }
}
