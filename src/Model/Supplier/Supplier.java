package Model.Supplier;

import java.util.Objects;

public class Supplier {
    private Integer id;
    private String name;

    public Supplier(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Supplier supplier = (Supplier) obj;

        return Objects.equals(id, supplier.id) &&
                Objects.equals(name, supplier.name);
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
