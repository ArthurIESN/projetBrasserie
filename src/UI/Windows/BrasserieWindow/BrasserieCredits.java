package UI.Windows.BrasserieWindow;

import UI.Components.GridBagLayoutHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BrasserieCredits extends JPanel
{
    private static final ArrayList<String> authors = new ArrayList<String>()
    {{
        add("Ryckbosch Arthur");
        add("Michaux Dorian");
        add("Martin Gregory");
    }};

    public BrasserieCredits() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Brasserie Credits");
        title.setFont(title.getFont().deriveFont(30.0f));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel subtitle = new JLabel("This software was made by");
        subtitle.setFont(subtitle.getFont().deriveFont(20.0f));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(subtitle);

        GridBagLayoutHelper gridBagLayoutHelper = new GridBagLayoutHelper();

        for (String author : authors) {
            JLabel authorLabel = new JLabel(author);
            authorLabel.setFont(authorLabel.getFont().deriveFont(18.0f));
            gridBagLayoutHelper.addField(authorLabel);
        }

        add(gridBagLayoutHelper);

        ImageIcon gifIcon = new ImageIcon(getClass().getResource("main/resources/images/dorianHome.gif"));
        JLabel gifLabel = new JLabel(gifIcon, JLabel.CENTER);
        gifLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(gifLabel);
    }
}