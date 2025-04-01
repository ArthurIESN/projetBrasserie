package UI.Components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class NavbarPanel extends JPanel
{

    public NavbarPanel(ArrayList<String> items, BiConsumer<Integer, Object> onItemClick)
    {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            int index = i;

            JButton button = new JButton(item);
            button.addActionListener(e ->
            {
                if (onItemClick != null)
                {
                    onItemClick.accept(index, null);
                }
            });

            add(button);
        }

        onItemClick.accept(0, null);
    }
}
