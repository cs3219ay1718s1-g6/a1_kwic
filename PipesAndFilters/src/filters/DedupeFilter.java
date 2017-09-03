package filters;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DedupeFilter extends Filter<String, String> {
    @Override
    protected Stream<String> transform(Stream<String> input) {
        return input.collect(Collectors.toCollection(LinkedHashSet::new)).stream();
    }
}
