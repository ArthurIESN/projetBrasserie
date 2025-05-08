package Model.Vat;

import java.util.Objects;

public class Vat {
    private String code;
    private float rate;

    public Vat(String code, float rate) {
        this.code = code;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public float getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Vat vat = (Vat) obj;

        return Objects.equals(code, vat.code) &&
                Objects.equals(rate, vat.rate);
    }

    public static int hashCode(String code, float rate) {
        return Objects.hash(code, rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, rate);
    }

    @Override
    public String toString()
    {
        return "Vat{ \n" +
                "       code=" + code + "\n" +
                "       rate=" + rate + "\n" +
                '}';
    }
}
