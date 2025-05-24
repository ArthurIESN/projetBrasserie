package UI.Windows.BrasserieWindow;

import UI.Components.GridBagLayoutHelper;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class BrasserieCredits extends JPanel
{
    private static final ArrayList<String> authors = new ArrayList<>()
    {{
        add("Ryckbosch Arthur");
        add("Michaux Dorian");
        add("Martin Gregory");
    }};

    public BrasserieCredits()
    {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        JPanel creditsPanel = new JPanel();

        creditsPanel.setLayout(new BoxLayout(creditsPanel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(creditsPanel);

        GridBagLayoutHelper gridBagLayoutHelper = new GridBagLayoutHelper();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Brasserie Credits");
        title.setFont(title.getFont().deriveFont(30.0f));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        gridBagLayoutHelper.addField(title);

        creditsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel subtitle = new JLabel("This software was made by");
        subtitle.setFont(subtitle.getFont().deriveFont(20.0f));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        gridBagLayoutHelper.addField(subtitle);

        creditsPanel.add(gridBagLayoutHelper);

        for (String author : authors)
        {
            JLabel authorLabel = new JLabel(author);
            authorLabel.setFont(authorLabel.getFont().deriveFont(18.0f));
            authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            creditsPanel.add(authorLabel);
        }

        URL gifUrl = getClass().getClassLoader().getResource("Images/dorianHome.gif");
        if (gifUrl == null) {
            throw new IllegalArgumentException("Image not found: Images/dorianHome.gif");
        }
        ImageIcon gifIcon = new ImageIcon(gifUrl);
        JLabel gifLabel = new JLabel(gifIcon, JLabel.CENTER);
        gifLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        creditsPanel.add(gifLabel);

        add(scrollPane);
    }
}