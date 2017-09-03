package component;

import listener.TaskCompleteListener;

import java.util.List;
import java.util.stream.Collectors;

public class Alphabetizer extends Tool {

    public Alphabetizer(TaskCompleteListener callback) {
        super(callback);
    }

    //-----------------------------------------------
    // Transformation
    //-----------------------------------------------
    public void transform(List<String> input) {
        this.data = input.stream();
        this.output = data.sorted(String::compareToIgnoreCase).collect(Collectors.toList());
        this.callback.onTaskComplete(this.output);
    }
}
