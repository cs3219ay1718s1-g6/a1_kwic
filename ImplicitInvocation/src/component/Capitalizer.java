package component;

import listener.TaskCompleteListener;

import java.util.List;

public class Capitalizer extends Tool {

    public Capitalizer(TaskCompleteListener callback) {
        super(callback);
    }

    //-----------------------------------------------
    // Transformation
    //-----------------------------------------------
    public void transform(List<String> input) {

        for(int lineIndex = 0; lineIndex < input.size(); lineIndex++) {
            String line = input.get(lineIndex);
            input.set(lineIndex, getFormattedText(line));

            this.output = input;
            this.callback.onTaskComplete(this.output);
        }
    }

    // return a string with the first character capitalized
    private String getFormattedText(String line) {
        return line.substring(0, 1).toUpperCase() + line.substring(1);
    }
}
