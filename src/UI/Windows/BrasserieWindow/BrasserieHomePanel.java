package UI.Windows.BrasserieWindow;

import javax.swing.*;
import java.awt.*;

public class BrasserieHomePanel extends JPanel {
    public BrasserieHomePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Welcome to Brasserie", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Center panel to hold welcome message and GIF
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Welcome message
        JLabel welcomeMessage = new JLabel("<html>Welcome to the Brasserie management system.</html>", JLabel.CENTER);
        welcomeMessage.setFont(new Font("Arial", Font.ITALIC, 18));
        welcomeMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(welcomeMessage);

        JLabel textMessage = new JLabel("<html>Use the menu above to navigate through the application.</html>", JLabel.CENTER);
        textMessage.setFont(new Font("Arial", Font.ITALIC, 18));
        textMessage.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        textMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(textMessage);

        add(centerPanel, BorderLayout.CENTER);
    }
}

