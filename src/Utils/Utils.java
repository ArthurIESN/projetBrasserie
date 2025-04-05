package Utils;

import Model.Process.Process;
import Model.ProcessStatus.ProcessStatus;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils
{
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
