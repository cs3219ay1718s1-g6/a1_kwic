package filters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CircularShiftFilter extends Filter<String, String> {

    @Override
    protected Stream<String> transform(Stream<String> input) {
        return input.flatMap(line -> {
            String[] words = line.split("\\s+");
            List<String> shiftedLines = new ArrayList<>();
            for (int startIndex = 0; startIndex < words.length; ++startIndex) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int wordIndex = 0; wordIndex < words.length; ++wordIndex) {
                    stringBuilder.append(words[(startIndex + wordIndex) % words.length]);
                    if (wordIndex < words.length - 1) {
                        stringBuilder.append(' ');
                    }
                }
                shiftedLines.add(stringBuilder.toString());
            }
            return shiftedLines.stream();
        });
    }
}
