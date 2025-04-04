import UI.BrasserieWindow.BrasserieWindow;
import Environement.SystemProperties;
import UI.Components.TableModelMaker;
import UI.Test.ProcessEnhancedTableModel;
import UI.Test.ProcessStatusEnhancedTableModel;

public class Main
{
    public static void main(String[] args)
    {
        SystemProperties.enableFlatDarkLaf();
        BrasserieWindow brasserieWindow = new BrasserieWindow();
    }
}