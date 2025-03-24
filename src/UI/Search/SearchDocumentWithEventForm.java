package UI.Search;

import Controller.SearchController;
import Exceptions.DataAccess.DatabaseConnectionFailedException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDocumentWithEventForm extends JPanel {
    private JLabel title;
    private List<Integer> years = new ArrayList<>();

    public SearchDocumentWithEventForm()  throws DatabaseConnectionFailedException {
        years = SearchController.getInstance().getDatesEvents();
        System.out.println(years);
    }
}
