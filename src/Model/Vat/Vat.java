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
    public String toString()
    {
        return "Vat{ \n" +
                "       code=" + code + "\n" +
                "       rate=" + rate + "\n" +
                '}';
    }
}
