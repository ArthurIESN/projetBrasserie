package UI.Theme;

import java.util.Properties;

public interface ThemeSubject
{
    void addObserver(ThemeObserver observer);
    void removeObserver(ThemeObserver observer);
    void notifyObservers(Properties themeProperties);
}
