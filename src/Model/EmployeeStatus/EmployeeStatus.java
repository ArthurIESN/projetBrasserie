package Model.EmployeeStatus;

public class EmployeeStatus {
    private Integer id;
    private String label;

    public EmployeeStatus(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return id + " - " + label;
    }
}
