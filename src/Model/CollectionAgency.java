package Model;

public class CollectionAgency {
    private Integer id;
    private String name;

    public CollectionAgency(Integer id,String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return "CollectionAgency{ \n" +
                "       id=" + id + ", \n" +
                "       name='" + name + '\n' +
                '}';
    }
}
