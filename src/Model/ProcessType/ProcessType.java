package Model.ProcessType;

import java.util.Objects;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ProcessType that = (ProcessType) obj;

        return Objects.equals(id, that.id) &&
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
