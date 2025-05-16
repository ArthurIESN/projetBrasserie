package Model.Event;

import Exceptions.Event.EventException;

import java.util.Date;
import java.util.Objects;

public class Event {
    private Integer id;
    private String label;
    private Date startDate;
    private Date endDate;
    private float impact;

    public Event(Integer id, String label, Date startDate, Date endDate, float impact) throws EventException
    {
        setId(id);
        setLabel(label);
        setStartDate(startDate);
        setEndDate(endDate);
        setImpact(impact);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) throws EventException
    {
        if(id == null || id <= 0)
        {
            throw new EventException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) throws EventException
    {
        if(label == null || label.isEmpty())
        {
            throw new EventException("Label cannot be null or empty");
        }
        this.label = label;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) throws EventException
    {
        if(startDate == null)
        {
            throw new EventException("Start date cannot be null");
        }
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) throws EventException
    {
        if(endDate == null)
        {
            throw new EventException("End date cannot be null");
        }
        this.endDate = endDate;
    }

    public float getImpact() {
        return impact;
    }

    public void setImpact(float impact) throws EventException
    {
        if(impact < 0)
        {
            throw new EventException("Impact cannot be negative");
        }
        this.impact = impact;
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

    public static int hashCode(Integer id, String label, Date startDate, Date endDate, float impact) {
        return Objects.hash(id, label, startDate, endDate, impact);
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
