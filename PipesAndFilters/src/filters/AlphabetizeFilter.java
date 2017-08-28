package filters;

import java.util.stream.Stream;

public class AlphabetizeFilter extends Filter<String, String> {

    @Override
    protected Stream<String> transform(Stream<String> input) {
        return input.sorted(String::compareToIgnoreCase);
    }
}
