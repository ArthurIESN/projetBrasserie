package Model.CollectionAgency;

import java.util.Objects;

public class CollectionAgency {
    private Integer id;
    private String name;

    public CollectionAgency(Integer id,String name){
        setId(id);
        this.name = name;
    }

    private void setId(Integer id)
    {
        if(id == null || id <= 0)
        {
            id = null;
        }
        else
        {
            this.id = id;
        }
    }

    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CollectionAgency that = (CollectionAgency) obj;

        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    public static int hashCode(Integer id, String name){
        return Objects.hash(id, name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name);
    }

    @Override
    public String toString(){
        return "CollectionAgency{ \n" +
                "       id=" + id + ", \n" +
                "       name='" + name + '\n' +
                '}';
    }
}
