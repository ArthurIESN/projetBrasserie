package Model.Supplier;

import Exceptions.Supplier.SupplierException;

import java.util.Objects;

public class Supplier {
    private Integer id;
    private String name;

    public Supplier(Integer id, String name) throws SupplierException {
        setId(id);
        setName(name);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) throws SupplierException {
       if(id == null && id <= 0){
           throw new SupplierException("ID cannot be null");
       }
       this.id = id;
    }

    public void setName(String name) throws SupplierException {
        if(name == null || name.isEmpty()){
            throw new SupplierException("Name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Supplier supplier = (Supplier) obj;

        return Objects.equals(id, supplier.id) &&
                Objects.equals(name, supplier.name);
    }

    public static int hashCode(Integer id, String name) {
        return Objects.hash(id, name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
