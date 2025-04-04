package UI.Components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

public class JNumberField extends JEnhancedTextField
{
    public enum NumberType
    {
        INTEGER,
        FLOAT
    }

    private final NumberType numberType;
    private final int decimalPlaces;
    private final int[] minMax = {Integer.MIN_VALUE, Integer.MAX_VALUE};
    private boolean allowNegative = true;

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
                validateNumber();
            }
        });
    }

    public JNumberField(NumberType numberType)
    {
        this(numberType, 0);
    }

    public JNumberField()
    {
        this(NumberType.INTEGER, 0);
    }

    public void setMinMax(int min, int max)
    {
        this.minMax[0] = min;
        this.minMax[1] = max;
    }

    public void setAllowNegative(boolean allowNegative)
    {
        this.allowNegative = allowNegative;
    }

    public int getInt()
    {
        validateNumber();
        String text = getText();
        if (text.isEmpty())
        {
            return 0;
        }
        return Integer.parseInt(text);
    }

    public float getFloat()
    {
        validateNumber();
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
            validateNumber();
            return;
        }

        if (e.getID() != KeyEvent.KEY_TYPED)
        {
            super.processKeyEvent(e);
            return;
        }

        char c = e.getKeyChar();
        String text = getText();

        switch (numberType) {
            case INTEGER:

                if(c == '-' && allowNegative && text.isEmpty())
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

                if(c == '-' && allowNegative && text.isEmpty())
                {
                    break;
                }

                if (!Character.isDigit(c) && c != '.') {
                    e.consume();
                    return;
                }

                // Ne pas autoriser deux points
                if (c == '.' && text.contains(".")) {
                    e.consume();
                    return;
                }

                // Si un point est déjà présent, on vérifie le nombre de décimales
                int pointIndex = text.indexOf('.');
                if (pointIndex != -1 && Character.isDigit(c)) {
                    int decimals = text.length() - pointIndex - 1;

                    // Position du curseur après le point ?
                    int caretPos = getCaretPosition();
                    if (caretPos > pointIndex && decimals >= decimalPlaces) {
                        e.consume();
                        return;
                    }
                }

                break;
        }

        super.processKeyEvent(e);
    }

    private void validateNumber()
    {
        String text = getText();
        switch (numberType)
        {
            case INTEGER:
                // check if the number is an integer

                if (!text.isEmpty() && !text.matches("\\d+"))
                {
                    updateText(text.replaceAll("\\D", ""));
                }

                // check if the number is within the min and max range
                if (!text.isEmpty())
                {
                    int number = Integer.parseInt(text);
                    if (number < minMax[0])
                    {
                        updateText(String.valueOf(minMax[0]));
                    }
                    else if (number > minMax[1])
                    {
                        updateText(String.valueOf(minMax[1]));
                    }
                }

                break;

            case FLOAT:
                // check if the number of decimal places is correct
                int pointIndex = text.indexOf('.');
                if (pointIndex != -1)
                {
                    int decimals = text.length() - pointIndex - 1;
                    if (decimals > decimalPlaces)
                    {
                        updateText(text.substring(0, pointIndex + decimalPlaces + 1));
                    }
                }

                // check if the number is within the min and max range
                if (!text.isEmpty())
                {
                    float number = Float.parseFloat(text);
                    if (number < minMax[0])
                    {
                        updateText(String.valueOf(minMax[0]));
                    }
                    else if (number > minMax[1])
                    {
                        updateText(String.valueOf(minMax[1]));
                    }
                }

                break;
        }
    }
}
