package pipes;

import filters.Filter;

import java.util.stream.Stream;

public class Pipe<I> {

    //-----------------------------------------------
    // Properties
    //-----------------------------------------------
    private Filter<I, ?> targetFilter;

    //-----------------------------------------------
    // Constructor
    //-----------------------------------------------
    public Pipe(Filter<I, ?> targetFilter) {
        this.targetFilter = targetFilter;
    }

    //-----------------------------------------------
    // Data
    //-----------------------------------------------
    public void receiveInput(Stream<I> inputStream) {
        this.targetFilter.receiveInput(inputStream);
    }

    //-----------------------------------------------
    // Hashing
    //-----------------------------------------------
    @Override
    public int hashCode() {
        return this.targetFilter.hashCode() * 37;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Pipe)) return false;
        Pipe<?> pipe = (Pipe<?>) o;
        return (this.targetFilter.equals(pipe.targetFilter));
    }
}
