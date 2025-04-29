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

import BusinessLogic.NumberLogic;
import Utils.Utils.NumberType;


public class JNumberField extends JEnhancedTextField
{

    private final NumberType numberType;
    private final int decimalPlaces;
    private final int[] minMax = {Integer.MIN_VALUE, Integer.MAX_VALUE};
    private boolean allowNegative = true;

    /**
     * Constructor for NumberLogic with specified number type and decimal places.
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
     * Constructor for NumberLogic with default decimal places.
     * It creates a field that accepts NumberType and has no decimal places.
     * @param numberType the type of number (INTEGER or FLOAT)
     */
    public JNumberField(NumberType numberType)
    {
        this(numberType, 0);
    }

    /**
     * Constructor for NumberLogic with default values.
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

                if(text.length() >= NumberLogic.getMaxIntLength())
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


                if(text.length() >=  NumberLogic.getMaxFloatLength())
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

                updateText(NumberLogic.validateInteger(text, allowNegative, minMax));
                break;

            case FLOAT:

                updateText(NumberLogic.validateFloat(text, allowNegative, decimalPlaces, minMax));
                break;
        }
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
