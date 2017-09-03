package component;

import listener.TaskCompleteListener;

import java.util.List;
import java.util.stream.Stream;

public abstract class Tool {

    //-----------------------------------------------
    // Required Variables
    //-----------------------------------------------
    protected Stream<String> data;
    protected List<String> output;
    protected TaskCompleteListener callback;

    public Tool(TaskCompleteListener callback) {
        this.callback = callback;
    }

    public abstract void transform(List<String> input);
}
