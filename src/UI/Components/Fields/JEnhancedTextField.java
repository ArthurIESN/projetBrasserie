package UI.Components.Fields;

import Environement.SystemProperties;

import UI.Theme.ThemeManager;
import UI.Theme.ThemeObserver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

// Handle placeholder text in JTextField
public class JEnhancedTextField extends JFormattedTextField implements ThemeObserver
{
    private static final Icon clearIcon = UIManager.getIcon("InternalFrame.closeIcon");
    private String placeholder;
    private boolean showingPlaceholder;
    private boolean canClear = true;
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

        setBorder(new EmptyBorder(5, 5, 5, 20));
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (canClear && isOverClearIcon(e.getX(), e.getY()))
                {
                    setText("");
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseMoved(MouseEvent e)
            {
                if (canClear && isOverClearIcon(e.getX(), e.getY()))
                {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else
                {
                    setCursor(Cursor.getDefaultCursor());
                }
            }
        });

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

        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.DISPLAYABILITY_CHANGED) != 0) {
                if (!isDisplayable()) {
                    dispose();
                }
            }
        });
    }

    private void dispose()
    {
        ThemeManager.getInstance().removeObserver(this);
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

    public boolean canClear()
    {
        return canClear;
    }

    public void setCanClear(boolean canClear)
    {
        this.canClear = canClear;

        repaint();
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

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        paintClearIcon(g);
    }

    private void paintClearIcon(Graphics g)
    {
        if (canClear)
        {
            int iconWidth = clearIcon.getIconWidth();
            int iconHeight = clearIcon.getIconHeight();
            int x = getWidth() - iconWidth - 5;
            int y = (getHeight() - iconHeight) / 2;
            clearIcon.paintIcon(this, g, x, y);
        }
    }

    private boolean isOverClearIcon(int mouseX, int mouseY)
    {
        int iconWidth = clearIcon.getIconWidth();
        int iconHeight = clearIcon.getIconHeight();
        int x = getWidth() - iconWidth - 5;
        int y = (getHeight() - iconHeight) / 2;
        return mouseX >= x && mouseX <= x + iconWidth && mouseY >= y && mouseY <= y + iconHeight;
    }
}
