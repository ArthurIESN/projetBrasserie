package Model.Type;

public class Type {
    private Integer id;
    private String label;

    public Type(Integer id, String label) {
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
