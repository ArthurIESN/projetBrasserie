package UI.Document;

import Model.Document.Document;
import UI.Components.Navbar.NavbarPanel;
import UI.Models.Document.DocumentObserver;
import UI.Models.Document.DocumentSubject;
import UI.Process.CreateProcessPanel;
import UI.Windows.WindowManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentPanel extends JPanel implements DocumentSubject {

    private final Container container;
    private final NavbarPanel navbarPanel;
    private static final List<DocumentObserver> observers= new ArrayList<>();

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

    public void navbarForceClick(int index){navbarPanel.forceClickItem(index);}

    @Override
    public void addObserver(DocumentObserver observer) {
       observers.add(observer);
    }

    @Override
    public void removeObserver(DocumentObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Document document) {
        for (DocumentObserver observer : observers) {
            observer.update(document);
        }
    }

    public void navBarClick(int index){
        navbarPanel.clickItem(index);
    }

    private void updateContent(int index){
        container.removeAll();

        Class<? extends JPanel> panelClass = getClassWithIndex(index);

        JPanel panel;

        try{
            panel = panelClass
                    .getDeclaredConstructor(DocumentPanel.class)
                    .newInstance(this);
        }catch (Exception e){
            panel = new CreateDocumentForm();
        }

        container.add(panel,BorderLayout.CENTER);
        container.revalidate();
        container.repaint();
    }

    public void moveTo(int index){
        Class<? extends JPanel> panelClass = getClassWithIndex(index);
        if(WindowManager.isPanelDisplayed(panelClass)){
            WindowManager.focusWindow(panelClass);
        }else {
            navbarPanel.clickItem(index);
        }
    }

    private Class<? extends JPanel> getClassWithIndex(int index){
        System.out.println("Index: " + index);
        return switch (index) {
           case 0 -> CreateDocumentForm.class;
           case 1 -> ReadDocumentForm.class;
           case 2 -> UpdateDocumentForm.class;
           case 3 -> DeleteDocumentForm.class;
           default -> CreateProcessPanel.class;
        };
    }

}
