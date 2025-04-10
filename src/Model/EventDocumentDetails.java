package Model;

import Model.DocumentDetails.DocumentDetails;
import Model.Event.Event;

import java.util.Objects;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        EventDocumentDetails that = (EventDocumentDetails) obj;

        return Objects.equals(id, that.id) &&
                Objects.equals(documentDetails, that.documentDetails) &&
                Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentDetails, event);
    }

    @Override
    public String toString() {
        return id + " - " + documentDetails + " - " + event;
    }
}
