package UI.Components.Navbar;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class NavbarPanel extends JPanel
{
    private int currentIndex = 0;

    public NavbarPanel(ArrayList<String> items, Consumer<Integer> onItemClick)
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
                    onItemClick.accept(index);
                }
            });

            add(button);
        }

        onItemClick.accept(0);
    }

    public void clickItem(int index)
    {
        if (index >= 0 && index < getComponentCount())
        {
            JButton button = (JButton) getComponent(index);
            button.doClick();

            currentIndex = index;
        }
    }
}
