package UI.Components.Fields;

import Environement.SystemProperties;
import UI.Theme.ThemeManager;
import UI.Theme.ThemeObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.HierarchyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Properties;

public class JDualSliderPanel extends JPanel implements ThemeObserver
{
    private static final int THUMB_RADIUS = 6;
    private final int[] values = {Integer.MIN_VALUE, Integer.MAX_VALUE};
    private boolean draggingThumb1 = false;
    private boolean draggingThumb2 = false;
    private final int padding = 60; // Left and right padding
    private int min;
    private int max;
    private static Color thumbColor;
    private static Color trackColor;
    private static Color textColor;


    @Override
    public void onThemeChanged(Properties themeProperties)
    {
        // We have nothing to update here (maybe later)
    }

    public static void onThemeChangedStatic(Properties themeProperties)
    {
        if(themeProperties != null)
        {
            thumbColor = Color.decode(themeProperties.getProperty("DualSlider.thumbColor"));
            trackColor = Color.decode(themeProperties.getProperty("DualSlider.trackColor"));
            textColor = Color.decode(themeProperties.getProperty("DualSlider.textColor"));
        }
    }

    public JDualSliderPanel(int minValue, int maxValue, int width, int height)
    {
        this.min = minValue;
        this.max = maxValue;
        setCurrentMin(min);
        setCurrentMax(max);
        updateSize(width, height);

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e)
            {
                int mouseX = e.getX();
                int thumb1X = xPositionForValue(getCurrentMin());
                int thumb2X = xPositionForValue(getCurrentMax());

                if (Math.abs(mouseX - thumb1X + THUMB_RADIUS) < Math.abs(mouseX - thumb2X)) {
                    draggingThumb1 = true;
                } else {
                    draggingThumb2 = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggingThumb1 = false;
                draggingThumb2 = false;
            }
        });

        addMouseMotionListener(new MouseAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent e)
            {
                int mouseX = e.getX();
                int newValue = valueForXPosition(mouseX);

                if (draggingThumb1)
                {
                    setCurrentMin(Math.min(Math.max(newValue, min), getCurrentMax()));
                } else if (draggingThumb2)
                {
                    setCurrentMax(Math.max(Math.min(newValue, max), getCurrentMin()));
                }

                repaint();
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

    public JDualSliderPanel(int minValue, int maxValue)
    {
        this(minValue, maxValue, 200, 50);
    }

    public JDualSliderPanel()
    {
        this(0, 100);
    }

    public void updateSize(int width, int height)
    {
        if(width < 0 || height < 0)
        {
            throw new IllegalArgumentException("Width and height must be greater than 0.");
        }

        setPreferredSize(new Dimension(width, height));
    }

    private int xPositionForValue(int value)
    {
        int trackLength = getWidth() - 2 * padding;
        return (int) ((double) (value - min) / (max - min) * trackLength) + padding;
    }

    private int valueForXPosition(int x)
    {
        int trackLength = getWidth() - 2 * padding;
        return (int) ((double) (x - padding) / trackLength * (max - min)) + min;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // draw track
        g2d.setColor(trackColor);
        g2d.fillRect(padding, getHeight() / 2 - 2, getWidth() - 2 * padding, 4);

        // draw thumbs
        int thumb1X = xPositionForValue(getCurrentMin());
        int thumb2X = xPositionForValue(getCurrentMax());

        drawThumb(g2d, thumb1X, getCurrentMin(), true);
        drawThumb(g2d, thumb2X, getCurrentMax(), false);

        // draw min and max values
        drawTextCentered(g2d, String.valueOf(min), padding / 2, getHeight() / 2, true);


        int maxTextWidth = g2d.getFontMetrics().stringWidth(String.valueOf(max));
        int maxX = getWidth() - padding - maxTextWidth;
        drawTextCentered(g2d, String.valueOf(max), maxX, getHeight() / 2, false);

        g2d.dispose();
    }

    private void drawThumb(Graphics2D g2d, int x, int value, boolean isLeftThumb)
    {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // add padding if values are equals
        int visualOffset = 0;
        if (getCurrentMin() == getCurrentMax()) {
            visualOffset = isLeftThumb ? -6 : 6;
        }

        g2d.setColor(thumbColor);
        g2d.fillOval(x - 6 + visualOffset, getHeight() / 2 - 6, 12, 12);
        g2d.setColor(textColor);
        g2d.drawOval(x - 6 + visualOffset, getHeight() / 2 - 6, 12, 12);

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(String.valueOf(value));
        int textHeight = fm.getHeight();
        int adjustedX = Math.min(Math.max(x - textWidth / 2 + visualOffset, 0), getWidth() - textWidth);
        int textY = isLeftThumb ? getHeight() / 2 - 10 : getHeight() / 2 + textHeight + 4;
        g2d.drawString(String.valueOf(value), adjustedX, textY);
    }

    private void drawTextCentered(Graphics2D g2d, String text, int x, int y, boolean isLeftText)
    {
        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        int adjustedX = isLeftText ? x - textWidth / 2 : x + (textWidth * 2);
        int adjustedY = y + textHeight / 4;
        g2d.setColor(textColor);
        g2d.drawString(text, adjustedX, adjustedY);
    }

    public int getCurrentMin()
    {
        return values[0];
    }

    public void setCurrentMin(int min)
    {
        if(min <= getCurrentMax() && min >= this.min)
        {
            values[0] = min;
        }
        else if(min < this.min)
        {
            values[0] = this.min;
        }

        repaint();
    }

    public int getCurrentMax()
    {
        return values[1];
    }

    public void setCurrentMax(int max)
    {
        if(max >= getCurrentMin() && max <= this.max)
        {
            values[1] = max;
        }
        else if(max > this.max)
        {
            values[1] = this.max;
        }

        repaint();
    }

    public int getMinValue()
    {
        return min;
    }

    public int getMaxValue()
    {
        return max;
    }

    public void setMinValue(int min)
    {
        this.min = min;
        values[0] = min;

        repaint();
    }

    public void setMaxValue(int max)
    {
        this.max = max;
        values[1] = max;

        repaint();
    }

    public void setMinMax(int min, int max)
    {
        this.min = min;
        this.max = max;

        repaint();

        values[0] = min;
        values[1] = max;
    }
}

