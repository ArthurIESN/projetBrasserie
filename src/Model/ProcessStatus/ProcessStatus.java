package Model.ProcessStatus;

import Exceptions.ProcessStatus.ProcessStatusException;

import java.util.Objects;

public class ProcessStatus {
    private Integer id;
    private String label;

    public ProcessStatus(Integer id, String label) throws ProcessStatusException {
        setId(id);
        setLabel(label);
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setId(Integer id) throws ProcessStatusException {
        if (id == null || id <= 0) {
            throw new ProcessStatusException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public void setLabel(String label) throws ProcessStatusException {
        if (label == null || label.isEmpty()) {
            throw new ProcessStatusException("Label cannot be null or empty");
        }
        this.label = label;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        ProcessStatus that = (ProcessStatus) obj;

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
