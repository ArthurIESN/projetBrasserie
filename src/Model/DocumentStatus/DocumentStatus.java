package Model.DocumentStatus;

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
    public String toString(){
        return "id : " + id + " label : " + label;
    }
}
