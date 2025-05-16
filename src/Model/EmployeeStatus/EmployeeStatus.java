package Model.EmployeeStatus;

import Exceptions.EmployeeStatus.EmployeeStatusException;

import java.util.Objects;

public class EmployeeStatus
{

    public enum Status
    {
        Suspended (0),
        On_leave (1),
        Retired(2),
        Active(4),
        Manager(8);

        private final int value;

        Status(int value)
        {
            this.value = value;
        }

        public int getValue()
        {
            return value;
        }

    }

    private Integer id;
    private String label;

    public EmployeeStatus(Integer id, String label)  throws EmployeeStatusException
    {
        setId(id);
        setLabel(label);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) throws EmployeeStatusException
    {
        if(id == null || id <= 0)
        {
            throw new EmployeeStatusException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) throws EmployeeStatusException
    {
        if(label == null || label.isEmpty())
        {
            throw new EmployeeStatusException("Label cannot be null or empty");
        }
        this.label = label;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        EmployeeStatus that = (EmployeeStatus) obj;

        return Objects.equals(id, that.id) &&
                Objects.equals(label, that.label);
    }

    public static int hashCode(Integer id, String label) {
        return Objects.hash(id, label);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, label);
    }

    @Override
    public String toString() {
        return id + " - " + label;
    }
}
