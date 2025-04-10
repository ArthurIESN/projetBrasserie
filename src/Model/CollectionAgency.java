package Model;

import java.util.Objects;

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
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CollectionAgency that = (CollectionAgency) obj;

        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
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
