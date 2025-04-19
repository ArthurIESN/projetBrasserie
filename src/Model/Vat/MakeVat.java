package Model.Vat;

import java.util.HashMap;

public class MakeVat
{
    private static final HashMap<Integer, Vat> vatMap = new HashMap<>();

    public static Vat getVat(String code, float rate)
    {
        Vat vat = new Vat(code, rate);
        int vatHash = vat.hashCode();

        if(vatMap.containsKey(vatHash))
        {
            return vatMap.get(vatHash);
        }
        else
        {
            vatMap.put(vatHash, vat);
            return vat;
        }
    }
}
