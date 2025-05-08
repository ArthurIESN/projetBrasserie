package UI.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class RoundedPanel extends JPanel {
    private final float topLeftRadius;
    private final float topRightRadius;
    private final float bottomLeftRadius;
    private final float bottomRightRadius;
    private final int thickness;

    public RoundedPanel(float topLeftRadius, float topRightRadius, float bottomLeftRadius, float bottomRightRadius, int thickness) {
        super();
        this.topLeftRadius = topLeftRadius;
        this.topRightRadius = topRightRadius;
        this.bottomLeftRadius = bottomLeftRadius;
        this.bottomRightRadius = bottomRightRadius;
        this.thickness = thickness;
        setOpaque(false);

        // set empty border to avoid flickering
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        int halfThickness = thickness / 2;

        Path2D path = new Path2D.Float();
        path.moveTo(halfThickness, topLeftRadius + halfThickness);

        if (topLeftRadius > 0)
            path.quadTo(halfThickness, halfThickness, topLeftRadius + halfThickness, halfThickness);
        else
            path.lineTo(halfThickness, halfThickness);

        path.lineTo(w - topRightRadius - halfThickness, halfThickness);

        if (topRightRadius > 0)
            path.quadTo(w - halfThickness, halfThickness, w - halfThickness, topRightRadius + halfThickness);
        else
            path.lineTo(w - halfThickness, halfThickness);

        path.lineTo(w - halfThickness, h - bottomRightRadius - halfThickness);

        if (bottomRightRadius > 0)
            path.quadTo(w - halfThickness, h - halfThickness, w - bottomRightRadius - halfThickness, h - halfThickness);
        else
            path.lineTo(w - halfThickness, h - halfThickness);

        path.lineTo(bottomLeftRadius + halfThickness, h - halfThickness);

        if (bottomLeftRadius > 0)
            path.quadTo(halfThickness, h - halfThickness, halfThickness, h - bottomLeftRadius - halfThickness);
        else
            path.lineTo(halfThickness, h - halfThickness);

        path.closePath();

        g2.setColor(getBackground());
        g2.fill(path);

        g2.setStroke(new BasicStroke(thickness));
        g2.setColor(Color.WHITE);
        g2.draw(path);

        g2.dispose();
    }
}
