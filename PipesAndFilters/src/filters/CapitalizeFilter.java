package filters;

import java.util.stream.Stream;

public class CapitalizeFilter extends Filter<String, String> {
    @Override
    protected Stream<String> transform(Stream<String> input) {
        return input.map(CapitalizeFilter::capitalizeFirstWord);
    }

    private static String capitalizeFirstWord(String string) {
        string = string.trim();
        int firstSpaceIndex = string.indexOf(' ');
        if (firstSpaceIndex == -1) {
            return string.substring(0, 1).toUpperCase().concat(string.substring(1));
        }
        return capitalizeFirstWord(string.substring(0, firstSpaceIndex)).concat(string.substring(firstSpaceIndex));
    }
}
