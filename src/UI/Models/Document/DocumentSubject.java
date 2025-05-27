package UI.Models.Document;

import Model.Document.Document;

public interface DocumentSubject {
    void addObserver(DocumentObserver observer);
    void removeObserver(DocumentObserver observer);
    void notifyObservers(Document document);
}
