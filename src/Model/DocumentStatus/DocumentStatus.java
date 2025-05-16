package Model.DocumentStatus;

import Exceptions.DocumentStatus.DocumentStatusException;

import java.util.Objects;

public class DocumentStatus {
    private Integer id;
    private String label;

    public DocumentStatus(Integer id, String label) throws DocumentStatusException
    {
        setId(id);
        setLabel(label);
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id) throws DocumentStatusException
    {
        if(id == null || id <= 0)
        {
            throw new DocumentStatusException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public String getLabel(){
        return label;
    }

    public void setLabel(String label) throws DocumentStatusException
    {
        if(label == null || label.isEmpty())
        {
            throw new DocumentStatusException("Label cannot be null or empty");
        }
        this.label = label;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        DocumentStatus that = (DocumentStatus) obj;

        return Objects.equals(id, that.id) &&
                Objects.equals(label, that.label);
    }

    public static int hashCode(Integer id, String label){
        return Objects.hash(id, label);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, label);
    }

    @Override
    public String toString(){
        return "id : " + id + " label : " + label;
    }
}
