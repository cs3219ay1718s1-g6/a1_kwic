package pipes;

import filters.Filter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pipeline<I, O> {

    //-----------------------------------------------
    // Properties
    //-----------------------------------------------
    private Filter<I, ?> inputFilter;

    //-----------------------------------------------
    // Constants
    //-----------------------------------------------
    @SuppressWarnings("unchecked")
    public Pipeline(List<O> reservoir, Filter... filters) {
        assert filters.length > 0;
        // Save the reference to the first filter
        inputFilter = filters[0];
        // Chain the filters one after another
        for (int index = 0; index < filters.length - 1; ++index) {
            filters[index].addPipe(filters[index + 1]);
        }
        // Pipe the last filter to a custom output filter
        // that pushes the data to the `reservoir`
        filters[filters.length - 1].addPipe(new PipelineOutputFilter<>(reservoir));
    }

    //-----------------------------------------------
    // Public Interface
    //-----------------------------------------------
    public void receiveInput(List<I> input) {
        inputFilter.receiveInput(input.parallelStream());
    }

    //-----------------------------------------------
    // Private Methods
    //-----------------------------------------------
    private static class PipelineOutputFilter<E> extends Filter<E, Void> {

        // Properties
        private List<E> reservoir;

        // Constructor
        private PipelineOutputFilter(List<E> reservoir) {
            this.reservoir = reservoir;
        }

        // Overrides
        @Override
        public void receiveInput(Stream<E> input) {
            reservoir.clear();
            reservoir.addAll(input.collect(Collectors.toList()));
        }

        @Override
        protected Stream<Void> transform(Stream<E> input) {
            return null;
        }
    }
}
