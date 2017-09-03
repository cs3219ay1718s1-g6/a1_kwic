package component;

import listener.TaskCompleteListener;

import java.util.List;
import java.util.stream.Collectors;

public class DistinctFilter extends Tool {

    public DistinctFilter(TaskCompleteListener callback) {
        super(callback);
    }

    //-----------------------------------------------
    // Transformation
    //-----------------------------------------------
    public void transform(List<String> input) {
        this.data = input.stream();
        this.output = data.distinct().collect(Collectors.toList());
        this.callback.onTaskComplete(this.output);
    }
}
