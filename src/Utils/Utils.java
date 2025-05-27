package Utils;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;




public class Utils
{
    /**
     * Type of number to be entered in the field (INTEGER or FLOAT).
     */
    public enum NumberType
    {
        INTEGER,
        FLOAT
    }

    public static <D, T> ArrayList<T> transformData(ArrayList<D> data, Function<D, T> mapper) {
        return data.stream()
                .map(item -> item == null ? null : mapper.apply(item))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static <D, T> ArrayList<T> transformData(D data, Function<D, T> mapper) {
        ArrayList<T> result = new ArrayList<>();
        if (data != null) {
            result.add(mapper.apply(data));
        }
        return result;
    }

}
