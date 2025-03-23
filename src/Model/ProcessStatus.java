package Model;

public class ProcessStatus {
    private Integer id;
    private String label;

    public ProcessStatus(Integer id, String label) {
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
