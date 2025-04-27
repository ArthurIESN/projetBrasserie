package UI.Components;

import javax.swing.*;
import java.awt.*;

public class JEnhancedProgressBar extends JPanel
{
    private float progress = 0.0f;
    private String message;


    public void setProgress(float progress)
    {
        this.progress = Math.max(0.0f, Math.min(1.0f, progress));
        System.out.println("Progress: " + this.progress);
        repaint();
    }

    public void setMessage(String message)
    {
        this.message = message;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        int progressWidth = (int) (getWidth() * progress);
        GradientPaint gradient = new GradientPaint(0, 0, Color.GREEN, progressWidth, 0, new Color(0, 150, 0));
        g2d.setPaint(gradient);
        g2d.fillRoundRect(0, 0, progressWidth, getHeight(), 20, 20);

        g2d.setColor(Color.BLACK);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        if (message != null)
        {
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            String progressText = message + " (" + (int) (progress * 100) + "%)";
            int textWidth = g2d.getFontMetrics().stringWidth(progressText);
            int textX = Math.max(0, (getWidth() - textWidth) / 2);
            int textY = getHeight() / 2 + g2d.getFontMetrics().getAscent() / 2 - 2;
            g2d.drawString(progressText, textX, textY);
        }
    }

}
