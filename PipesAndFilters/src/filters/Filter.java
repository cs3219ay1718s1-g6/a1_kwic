package filters;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class Filter<I, O> {

    //-----------------------------------------------
    // Properties
    //-----------------------------------------------
    private Set<Filter<O, ?>> outputFilters;

    //-----------------------------------------------
    // Constructor
    //-----------------------------------------------
    public Filter() {
        outputFilters = new LinkedHashSet<>();
    }

    //-----------------------------------------------
    // Public Methods
    //-----------------------------------------------
    public void receiveInput(Stream<I> input) {
        Stream<O> output = this.transform(input);
        for (Filter<O, ?> outputFilter : this.outputFilters) {
            outputFilter.receiveInput(output);
        }
    }

    public <E> void addPipe(Filter<O, E> nextFilter) {
        this.outputFilters.add(nextFilter);
    }

    public <E> void removePipe(Filter<O, E> nextFilter) {
        this.outputFilters.remove(nextFilter);
    }

    //-----------------------------------------------
    // Abstract Methods
    //-----------------------------------------------
    protected abstract Stream<O> transform(Stream<I> input);

}
