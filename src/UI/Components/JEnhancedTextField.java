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

    public JEnhancedTextField()
    {
        this.showingPlaceholder = true;
        setPreferredSize(new Dimension(300, 25));

        // Add focus listener to manage placeholder visibility
        this.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                if (showingPlaceholder)
                {
                    setForeground(Color.BLACK);
                    setText("");
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                setPlaceholderText();
            }
        });
    }

    @Override
    public String getText()
    {
        return showingPlaceholder ? "" : super.getText();
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
            setText(placeholder);
            setForeground(Color.GRAY);
            showingPlaceholder = true;
        }
    }
}
