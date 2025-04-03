package Model;

import Model.Event.Event;

public class EventDocumentDetails {
    private Integer id;
    private DocumentDetails documentDetails;
    private Event event;

    public EventDocumentDetails(Integer id, DocumentDetails documentDetails, Event event) {
        this.id = id;
        this.documentDetails = documentDetails;
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public DocumentDetails getDocumentDetails() {
        return documentDetails;
    }

    public Event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return id + " - " + documentDetails + " - " + event;
    }
}
