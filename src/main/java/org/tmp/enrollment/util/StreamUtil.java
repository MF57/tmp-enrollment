package org.tmp.enrollment.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamUtil {

    public static <I, O> List<O> mapToList(Collection<I> input, Function<? super I, O> mapper) {
        return input.stream()
                .map(mapper)
                .collect(Collectors.toList());
    }

    public static <T> int count(Predicate<? super T> predicate, T ... items) {
        return (int) Arrays.stream(items).filter(predicate).count();
    }
}
