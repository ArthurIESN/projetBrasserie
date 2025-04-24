package UI.Components.Fields;

import Environement.SystemProperties;

import UI.Theme.ThemeManager;
import UI.Theme.ThemeObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Properties;

// Handle placeholder text in JTextField
public class JEnhancedTextField extends JFormattedTextField implements ThemeObserver
{
    private String placeholder;
    private boolean showingPlaceholder;
    private static Color textColor;
    private static Color placeholderColor;


    public static void onThemeChangedStatic(Properties themeProperties)
    {
        if (themeProperties != null)
        {
            textColor = Color.decode(themeProperties.getProperty("EnhancedTextField.textColor"));
            placeholderColor = Color.decode(themeProperties.getProperty("EnhancedTextField.placeholderColor"));
        }
    }

    @Override
    public void onThemeChanged(Properties themeProperties)
    {
        if(showingPlaceholder)
        {
            setForeground(placeholderColor);
        }
        else
        {
            setForeground(textColor);
        }
    }

    public JEnhancedTextField()
    {
        ThemeManager.getInstance().addObserver(this);

        this.showingPlaceholder = true;
        setPreferredSize(new Dimension(300, 25));

        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));

        // Add focus listener to manage placeholder visibility
        this.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                if (showingPlaceholder)
                {
                    setForeground(textColor);
                    setText("");
                    showingPlaceholder = false;
                }

                setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                setPlaceholderText();

                setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            }
        });
    }

    @Override
    public String getText()
    {
        return showingPlaceholder ? "" : super.getText();
    }

    public void updateText(String text)
    {
        setText(text);

        if(!text.isEmpty())
        {
            setForeground(textColor);
            showingPlaceholder = false;
        }
        else
        {
            setPlaceholderText();
        }
    }

    public void setPlaceholder(String placeholder)
    {
        this.placeholder = placeholder;
        setPlaceholderText();
    }

    private void setPlaceholderText()
    {
        if (getText().isEmpty())
        {
            showingPlaceholder = true;
            setText(placeholder);
            setForeground(placeholderColor);
        }
    }
}
