package Model.CustomerStatus;

import Exceptions.CustomerStatus.CustomerStatusException;

import java.util.Objects;

public class CustomerStatus
{

    private Integer id;
    private String label;

    public CustomerStatus(Integer id, String label) throws CustomerStatusException
    {
        setId(id);
        setLabel(label);
    }

    private void setId(Integer id) throws CustomerStatusException
    {
        if(id == null || id <= 0)
        {
            throw new CustomerStatusException("CustomerStatus ID must be a positive integer.");
        }

        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) throws CustomerStatusException
    {
        if(label == null || label.isEmpty())
        {
            throw new CustomerStatusException("CustomerStatus label cannot be null or empty.");
        }

        this.label = label;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CustomerStatus that = (CustomerStatus) obj;

        return  Objects.equals(id, that.id) &&
                Objects.equals(label, that.label);
    }

    public static int hashCode(Integer id, String label) {
        return Objects.hash(id, label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @Override
    public String toString() {
        return id + " - " + label;
    }
}
