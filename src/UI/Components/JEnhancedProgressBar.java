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
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int arc = 10;
        int width = getWidth();
        int height = getHeight();

        g2d.setColor(new Color(245, 245, 245));
        g2d.fillRoundRect(0, 0, width, height, arc, arc);

        int progressWidth = (int) (width * progress);
        if (progressWidth > 0) {
            g2d.setColor(new Color(0, 122, 255));
            g2d.fillRoundRect(0, 0, progressWidth, height, arc, arc);
        }

        g2d.setColor(new Color(200, 200, 200));
        g2d.drawRoundRect(0, 0, width - 1, height - 1, arc, arc);

        if (message != null) {
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 13));
            String progressText = message + " (" + (int) (progress * 100) + "%)";
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(progressText);
            int textX = Math.max(0, (width - textWidth) / 2);
            int textY = height / 2 + fm.getAscent() / 2 - 2;

            Shape oldClip = g2d.getClip();

            g2d.setClip(0, 0, progressWidth, height);
            g2d.setColor(Color.WHITE);
            g2d.drawString(progressText, textX, textY);

            g2d.setClip(progressWidth, 0, width - progressWidth, height);
            g2d.setColor(new Color(60, 60, 60));
            g2d.drawString(progressText, textX, textY);

            g2d.setClip(oldClip);
        }

        g2d.dispose();
    }


}
