package Model.CollectionAgency;

import Exceptions.CollectionAgency.CollectionAgencyException;

import java.util.Objects;

public class CollectionAgency {
    private Integer id;
    private String name;

    public CollectionAgency(Integer id,String name) throws CollectionAgencyException
    {
        setId(id);
        setName(name);
    }


    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id) throws CollectionAgencyException
    {
        if(id == null || id <= 0)
        {
            throw new CollectionAgencyException("ID must be a positive integer");
        }

        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name) throws CollectionAgencyException
    {

        if(name == null || name.isEmpty())
        {
            throw new CollectionAgencyException("Name cannot be null or empty");
        }

        this.name = name;
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
