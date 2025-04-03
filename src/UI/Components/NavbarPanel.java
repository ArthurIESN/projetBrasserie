package UI.Components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class NavbarPanel extends JPanel
{
    private int currentIndex = 0;

    public NavbarPanel(ArrayList<String> items, BiConsumer<Integer, Object> onItemClick)
    {
        setLayout(new FlowLayout(FlowLayout.CENTER));

        for (int i = 0; i < items.size(); i++)
        {
            String item = items.get(i);
            int index = i;

            JButton button = new JButton(item);
            button.addActionListener(e ->
            {
                if (onItemClick != null && currentIndex != index)
                {
                    currentIndex = index;
                    onItemClick.accept(index, null);
                }
            });

            add(button);
        }

        onItemClick.accept(0, null);
    }

    public void clickItem(int index)
    {
        if (index >= 0 && index < getComponentCount())
        {
            JButton button = (JButton) getComponent(index);
            button.doClick();
        }
    }
}
