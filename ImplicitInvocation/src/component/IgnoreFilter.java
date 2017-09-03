package component;

import listener.TaskCompleteListener;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class IgnoreFilter extends Tool {

    //-----------------------------------------------
    // Constants
    //-----------------------------------------------
    private static final String WORDS_TO_IGNORE = "is the of and as a after";
    private static final Set<String> IGNORE_SET = new HashSet<>();
    static {
        IGNORE_SET.addAll(Arrays.asList(WORDS_TO_IGNORE.split("\\s+")));
    }

    public IgnoreFilter() { }

    public IgnoreFilter(TaskCompleteListener callback) {
        super(callback);
    }

    //-----------------------------------------------
    // Transformation
    //-----------------------------------------------
    public void transform(List<String> input) {
        this.data = input.stream();

        this.output = this.data.filter(string -> {
            int firstSpaceIndex = string.indexOf(' ');
            String stringToCheck = string;
            if (firstSpaceIndex != -1) {
                stringToCheck = string.substring(0, firstSpaceIndex);
            }
            return !IGNORE_SET.contains(stringToCheck.toLowerCase());
        }).collect(Collectors.toList());

        this.callback(this.output);
    }
}

