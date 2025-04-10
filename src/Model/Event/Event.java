package Model.Event;

import java.util.Date;
import java.util.Objects;

public class Event {
    private Integer id;
    private String label;
    private Date startDate;
    private Date endDate;
    private float impact;

    public Event(Integer id, String label, Date startDate, Date endDate, float impact) {
        this.id = id;
        this.label = label;
        this.startDate = startDate;
        this.endDate = endDate;
        this.impact = impact;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public float getImpact() {
        return impact;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Event event = (Event) obj;

        return Objects.equals(id, event.id) &&
                Objects.equals(label, event.label) &&
                Objects.equals(startDate, event.startDate) &&
                Objects.equals(endDate, event.endDate) &&
                Objects.equals(impact, event.impact);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, label, startDate, endDate, impact);
    }

    @Override
    public String toString() {
        return id + " - " + label + " - " + startDate + " - " + endDate + " - " + impact;
    }

}
