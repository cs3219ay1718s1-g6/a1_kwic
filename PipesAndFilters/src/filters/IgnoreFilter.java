package filters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class IgnoreFilter extends Filter<String, String> {

    //-----------------------------------------------
    // Constants
    //-----------------------------------------------
    private static final String WORDS_TO_IGNORE = "is the of and as a after";
    private static final Set<String> IGNORE_SET = new HashSet<>();
    static {
        IGNORE_SET.addAll(Arrays.asList(WORDS_TO_IGNORE.split("\\s+")));
    }

    //-----------------------------------------------
    // Transformation
    //-----------------------------------------------

    @Override
    protected Stream<String> transform(Stream<String> input) {
        return input.filter(string -> {
            int firstSpaceIndex = string.indexOf(' ');
            String stringToCheck = string;
            if (firstSpaceIndex != -1) {
                stringToCheck = string.substring(0, firstSpaceIndex);
            }
            return !IGNORE_SET.contains(stringToCheck.toLowerCase());
        });
    }
}
