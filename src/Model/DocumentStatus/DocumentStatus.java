package Model.DocumentStatus;

import java.util.Objects;

public class DocumentStatus {
    private Integer id;
    private String label;

    public DocumentStatus(Integer id, String label){
        this.id = id;
        this.label = label;
    }

    public Integer getId(){
        return id;
    }

    public String getLabel(){
        return label;
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
