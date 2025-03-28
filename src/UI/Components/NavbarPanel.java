package UI.Components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

public class NavbarPanel extends JPanel
{
    private ArrayList<String> items;
    private Consumer<Integer> onItemClick;

    public NavbarPanel(ArrayList<String> items, Consumer<Integer> onItemClick)
    {
        this.items = items;
        this.onItemClick = onItemClick;

        setLayout(new FlowLayout(FlowLayout.CENTER));

        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            int index = i;

            JButton button = new JButton(item);
            button.addActionListener(e ->
            {
                if (onItemClick != null)
                {
                    onItemClick.accept(index);
                }
            });

            add(button);
        }

        onItemClick.accept(0);
    }
}
