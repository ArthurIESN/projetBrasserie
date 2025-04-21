package UI.Process;

import Model.Process.Process;

public interface ProcessSubject
{
    void addObserver(ProcessObserver observer);
    void removeObserver(ProcessObserver observer);
    void notifyObservers(Process process);
}
