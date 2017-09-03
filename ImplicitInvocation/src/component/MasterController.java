package component;

import listener.TaskCompleteListener;

import java.util.List;

public class MasterController {

    //-----------------------------------------------
    // Required Variables
    //-----------------------------------------------
    private List<String> reservoir;
    private CircularShifter circularShifter;
    private IgnoreFilter ignoreFilter;
    private Capitalizer capitalizer;
    private Alphabetizer alphabetizer;
    private DistinctFilter distinctFilter;

    public MasterController(List<String> reservoir) {
        this.reservoir = reservoir;
        this.circularShifter = new CircularShifter(circularShifterCompleteListener);
        this.ignoreFilter = new IgnoreFilter(ignoreFilterCompleteListener);
        this.capitalizer = new Capitalizer(capitalizerCompleteListener);
        this.alphabetizer = new Alphabetizer(alphabetizerCompleteListener);
        this.distinctFilter = new DistinctFilter(distinctFilterCompleteListener);
    }

    public void triggerUpdate(List<String> input) {
        //triggers first component to start the process
        this.circularShifter.transform(input);
    }

    //-----------------------------------------------
    // Task Complete Listeners (For each component)
    //-----------------------------------------------
    private TaskCompleteListener circularShifterCompleteListener = output -> {
        this.ignoreFilter.transform(output);
    };

    private TaskCompleteListener ignoreFilterCompleteListener = output -> {
        this.capitalizer.transform(output);
    };

    private TaskCompleteListener capitalizerCompleteListener = output -> {
        this.alphabetizer.transform(output);
    };

    private TaskCompleteListener alphabetizerCompleteListener = output -> {
        this.distinctFilter.transform(output);
    };

    private TaskCompleteListener distinctFilterCompleteListener = output -> {
        //handles output update back to reservoir
        this.reservoir.clear();
        this.reservoir.addAll(output);
    };
}
