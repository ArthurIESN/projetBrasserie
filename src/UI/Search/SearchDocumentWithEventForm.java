package UI.Search;

import Controller.SearchController;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDocumentWithEventForm extends JPanel {
    private JLabel title;
    private List<Integer> years = new ArrayList<>();

    public SearchDocumentWithEventForm(){
        years = SearchController.getInstance().getDatesEvents();
        System.out.println(years);
    }
}
