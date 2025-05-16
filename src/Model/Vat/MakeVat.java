package Model.Vat;

import Exceptions.Vat.VatException;

import javax.swing.*;
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
            Vat vat;
            try{
                vat = new Vat(code, rate);
                vatMap.put(vatHash, vat);

            }catch (VatException e){
                vat = null;
                System.err.println("Error creating Vat: " + e.getMessage());
            }
            return vat;
        }
    }
}
