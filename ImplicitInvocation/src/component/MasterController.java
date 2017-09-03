package component;

import listener.TaskCompleteListener;

import java.util.List;

public class MasterController {

    //-----------------------------------------------
    // Required Variables
    //-----------------------------------------------
    private List<String> reservoir;
    private Alphabetizer alphabetizer;
    private CircularShifter circularShifter;
    private IgnoreFilter ignoreFilter;
    private DistinctFilter distinctFilter;

    public MasterController(List<String> reservoir) {
        this.reservoir = reservoir;
        this.alphabetizer = new Alphabetizer(alphabetizerCompleteListener);
        this.circularShifter = new CircularShifter(circularShifterCompleteListener);
        this.ignoreFilter = new IgnoreFilter(ignoreFilterCompleteListener);
        this.distinctFilter = new DistinctFilter(distinctFilterCompleteListener);
    }

    public void triggerUpdate(List<String> input) {
        //triggers first component to start the process
        this.alphabetizer.transform(input);
    }

    //-----------------------------------------------
    // Task Complete Listeners (For each component)
    //-----------------------------------------------
    private TaskCompleteListener alphabetizerCompleteListener = output -> {
        this.circularShifter.transform(output);
    };

    private TaskCompleteListener circularShifterCompleteListener = output -> {
        this.ignoreFilter.transform(output);
    };

    private TaskCompleteListener ignoreFilterCompleteListener = output -> {
        this.distinctFilter.transform(output);
    };

    private TaskCompleteListener distinctFilterCompleteListener = output -> {
        //handles output update back to reservoir
        this.reservoir.clear();
        this.reservoir.addAll(output);
    };
}
