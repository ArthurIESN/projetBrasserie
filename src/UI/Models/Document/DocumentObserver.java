package UI.Models.Document;

import Model.Document.Document;

public interface DocumentObserver {
    void update(Document document);
}
