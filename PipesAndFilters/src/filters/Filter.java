package filters;

import pipes.Pipe;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

public abstract class Filter<I, O> {

    //-----------------------------------------------
    // Properties
    //-----------------------------------------------
    private Set<Pipe<O>> connectedPipes;

    //-----------------------------------------------
    // Constructor
    //-----------------------------------------------
    protected Filter() {
        connectedPipes = new LinkedHashSet<>();
    }

    //-----------------------------------------------
    // Public Methods
    //-----------------------------------------------
    public void receiveInput(Stream<I> input) {
        Stream<O> output = this.transform(input);
        for (Pipe<O> outgoingPipe : this.connectedPipes) {
            outgoingPipe.receiveInput(output);
        }
    }

    public void addPipe(Pipe<O> pipe) {
        this.connectedPipes.add(pipe);
    }

    public void removePipe(Pipe<O> pipe) {
        this.connectedPipes.remove(pipe);
    }

    //-----------------------------------------------
    // Abstract Methods
    //-----------------------------------------------
    protected abstract Stream<O> transform(Stream<I> input);

}
