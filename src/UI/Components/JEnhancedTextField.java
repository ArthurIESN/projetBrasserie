package UI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

// Handle placeholder text in JTextField
public class JEnhancedTextField extends JTextField
{
    private String placeholder;
    private boolean showingPlaceholder;
    private static final Color textColor = Color.WHITE;
    private static final Color placeholderColor = Color.GRAY;

    public JEnhancedTextField()
    {
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
        setForeground(textColor);
        showingPlaceholder = false;
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
