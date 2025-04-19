package UI.Windows;

public interface WindowSubject
{
    void addObserver(WindowObserver observer);
    void removeObserver(WindowObserver observer);
    void notifyThemeChanged();
}
