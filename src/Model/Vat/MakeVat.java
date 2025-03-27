package Model.Vat;

import java.util.HashMap;

public class MakeVat
{
    private static final HashMap<String, Vat> vatMap = new HashMap<>();

    public static Vat getVat(String code, float rate)
    {
        if(vatMap.containsKey(code))
        {
            return vatMap.get(code);
        }
        else
        {
            Vat vat = new Vat(code, rate);
            vatMap.put(code, vat);
            return vat;
        }
    }
}
