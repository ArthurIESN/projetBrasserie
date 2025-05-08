package Model.CustomerStatus;

import java.util.Objects;

public class CustomerStatus {
    private Integer id;
    private String label;

    public CustomerStatus(Integer id, String label) {
        setId(id);
        this.label = label;
    }

    private void setId(Integer id)
    {
        if(id == null || id <= 0)
        {
            id = null;
        }
        else
        {
            this.id = id;
        }
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
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
