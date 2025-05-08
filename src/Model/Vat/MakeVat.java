package Model.Vat;

import java.util.HashMap;

public class MakeVat
{
    private static final HashMap<Integer, Vat> vatMap = new HashMap<>();

    public static Vat getVat(String code, float rate)
    {
        int vatHash = Vat.hashCode(code, rate);

        if(vatMap.containsKey(vatHash))
        {
            return vatMap.get(vatHash);
        }
        else
        {
            Vat vat = new Vat(code, rate);
            vatMap.put(vatHash, vat);
            return vat;
        }
    }
}
