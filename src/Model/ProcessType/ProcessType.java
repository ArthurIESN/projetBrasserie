package Model.ProcessType;

public class ProcessType {
    private Integer id;
    private String label;

    public ProcessType(Integer id, String label) {
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
