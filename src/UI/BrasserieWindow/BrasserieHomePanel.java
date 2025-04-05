package UI.BrasserieWindow;

import Controller.AppController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;
import Exceptions.Process.GetAllProcessesException;
import Model.ProcessStatus.ProcessStatus;
import UI.Components.JEnhancedTableScrollPanel;
import UI.Components.TableModelMaker;
import UI.Models.CustomerEnhancedTableModel;
import UI.Models.ProcessEnhancedTableModel;
import UI.Models.ProcessStatusEnhancedTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import UI.Models.ProcessTypeEnhancedTableModel;
import Utils.Utils;

import Model.Process.Process;
import Model.ProcessType.ProcessType;
import Model.Customer.Customer;
public class BrasserieHomePanel extends JPanel {
    public BrasserieHomePanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        // Title
        JLabel titleLabel = new JLabel("Welcome to Brasserie", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32)); // Larger font size for the title
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Add margin below the title
        add(titleLabel, BorderLayout.NORTH);

        // Center panel to hold welcome message and GIF
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Welcome message
        JLabel welcomeMessage = new JLabel("<html>Welcome to the Brasserie management system.<br>Use the menu above to navigate through the application.</html>", JLabel.CENTER);
        welcomeMessage.setFont(new Font("Arial", Font.ITALIC, 18)); // Different font style and size for the message
        welcomeMessage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the message
        welcomeMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(welcomeMessage);

        add(centerPanel, BorderLayout.CENTER);
    }
}

