package Model.ProcessType;

import Exceptions.ProcessType.ProcessTypeException;

import java.util.Objects;

public class ProcessType {
    private Integer id;
    private String label;

    public ProcessType(Integer id, String label) throws ProcessTypeException {
        setId(id);
        setLabel(label);
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setId(Integer id) throws ProcessTypeException {
        if(id == null || id <= 0){
            throw new ProcessTypeException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public void setLabel(String label) throws ProcessTypeException {
        if(label == null || label.isEmpty()){
            throw new ProcessTypeException("Label cannot be null or empty");
        }
        this.label = label;
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
