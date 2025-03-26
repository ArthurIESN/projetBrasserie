package UI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

// Handle placeholder text in JTextField
public class JEnhancedTextField extends JTextField
{
    private final String placeholder;
    private boolean showingPlaceholder;

    public JEnhancedTextField(String placeholder)
    {
        this.placeholder = placeholder;
        this.showingPlaceholder = true;

        // Add focus listener to manage placeholder visibility
        this.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                if (showingPlaceholder)
                {
                    setText("");
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if (getText().isEmpty())
                {
                    setText(placeholder);
                    showingPlaceholder = true;
                }
            }
        });
    }

    @Override
    public String getText()
    {
        return showingPlaceholder ? "" : super.getText();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if (showingPlaceholder && !isFocusOwner())
        {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setColor(Color.GRAY);
            g2.drawString(placeholder, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
            g2.dispose();
        }
    }
}
