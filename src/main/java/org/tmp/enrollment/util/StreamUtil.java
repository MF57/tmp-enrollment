package org.tmp.enrollment.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamUtil {

    public static <I, O> List<O> mapToList(Collection<I> input, Function<? super I, O> mapper) {
        return input.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

}
