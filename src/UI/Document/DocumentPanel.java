package UI.Document;

import Model.Document.Document;
import UI.Components.Navbar.NavbarPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DocumentPanel extends JPanel {
    private Container container;
    private NavbarPanel navbarPanel;

    public DocumentPanel(){
        setLayout(new BorderLayout());

        container = new JPanel();
        container.setLayout(new BorderLayout());

        ArrayList<String> items = new ArrayList<>();


        items.add("Create Document");
        items.add("Read Document");
        items.add("Update Document");
        items.add("Delete Document");

        navbarPanel = new NavbarPanel(items, this::updateContent);
        add(navbarPanel,BorderLayout.NORTH);
        add(container,BorderLayout.CENTER);
    }


    public void navBarClick(int index){
        navbarPanel.clickItem(index);
    }

    private void updateContent(int index){
        container.removeAll();

        JPanel jPanel = switch (index){
            case 0 -> new CreateDocumentForm();
            case 1 -> new ReadDocumentForm(this);
            case 2 -> new UpdateDocumentForm(this);
            case 3 -> new DeleteDocumentForm(this);
            default -> new DocumentPanel();
        };

        container.add(jPanel,BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }

}
