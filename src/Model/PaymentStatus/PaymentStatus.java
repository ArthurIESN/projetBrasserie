package Model.PaymentStatus;

import Exceptions.PaymentStatus.PaymentStatusException;

import java.util.Objects;

public class PaymentStatus {
    private int id;
    private String label;

    public PaymentStatus(int id, String label) throws PaymentStatusException {
        setId(id);
        setLabel(label);
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setId(Integer id) throws PaymentStatusException {
        if (id == null || id <= 0) {
            throw new PaymentStatusException("ID cannot be null or less than or equal to 0");
        }
        this.id = id;
    }

    public void setLabel(String label) throws PaymentStatusException {
        if (label == null || label.isEmpty()) {
            throw new PaymentStatusException("Label cannot be null or empty");
        }
        this.label = label;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PaymentStatus that = (PaymentStatus) obj;

        return Objects.equals(id, that.id) &&
                Objects.equals(label, that.label);
    }

    public static int hashCode(int id, String label) {
        return Objects.hash(id, label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }
}
