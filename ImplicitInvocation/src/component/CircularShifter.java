package component;

import listener.TaskCompleteListener;

import java.util.ArrayList;
import java.util.List;

public class CircularShifter extends Tool {

    public CircularShifter() {}

    public CircularShifter(TaskCompleteListener callback) { super(callback); }

    //-----------------------------------------------
    // Transformation
    //-----------------------------------------------
    public void transform(List<String> input) {

        //instantiate a new copy of shiftedLines
        this.output = new ArrayList<>();

        for(int lineIndex = 0; lineIndex < input.size(); lineIndex++) {
            String line = input.get(lineIndex);
            String[] words = line.split("\\s+");
            for (int startIndex = 0; startIndex < words.length; ++startIndex) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int wordIndex = 0; wordIndex < words.length; ++wordIndex) {
                    stringBuilder.append(words[(startIndex + wordIndex) % words.length]);
                    if (wordIndex < words.length - 1) {
                        stringBuilder.append(' ');
                    }
                }
                output.add(stringBuilder.toString());
            }
            this.callback(output);
        }
    }
}
