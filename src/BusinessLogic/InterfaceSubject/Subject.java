package BusinessLogic.InterfaceSubject;

import BusinessLogic.InterfaceObserver.Observer;

public interface Subject {
    void saveObserver(Observer o);
    void deleteObserver(Observer o);
    void notifyObservers();
}
