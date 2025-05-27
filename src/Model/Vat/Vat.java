package Model.Vat;

import Exceptions.Vat.VatException;

import java.util.Objects;

public class Vat {
    private String code;
    private float rate;

    public Vat(String code, float rate) throws VatException {
        setCode(code);
        setRate(rate);
    }

    public String getCode() {
        return code;
    }

    public float getRate() {
        return rate;
    }

    public void setCode(String code) throws VatException {
        if(code == null){
            throw new VatException("Code cannot be null");
        }
        this.code = code;
    }

    public void setRate(float rate) throws VatException{
        if(rate < 0 || rate > 100)
        {
            throw new VatException("Rate must be between 0 and 100");
        }
        this.rate = rate;
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
