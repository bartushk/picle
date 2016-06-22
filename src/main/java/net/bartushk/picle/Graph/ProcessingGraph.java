package net.bartushk.picle.Graph;

import java.io.Console;
import java.util.HashMap;

import net.bartushk.picle.Core.*;
import net.bartushk.picle.Graph.Exceptions.IncompleteGraphException;

/**
 *
 * A processing graph is a combination of a series of processing nodes. The processing graph does 
 * work asyncrhonously on a variable number of inputs, producing a variable number of outputs.
 *
 * <p> A processing graph must be properly connected in order to run. This means that every node
 * in this graph must have all of its inputs and outputs satisfied. 
 *
 * <p> The main entry and exit points into the graph are controled by the exit and entry nodes.
 * All output is fed back to the outputHandler when it is ready. 
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class ProcessingGraph<T> extends Graph 
{
    private EntryNode<T> entryNode; 
    private ExitNode<T> exitNode;
    private IResourceResolver<T> resolver;
    private IGraphOutputHandler<T> outputHandler;

    public ProcessingGraph(){
        this( new DictionaryResourceResolver<T>(), new LoggingOutputHandler<T>());
    }

    public ProcessingGraph(IResourceResolver<T> resolver, IGraphOutputHandler<T> outputHandler){
        super();
        this.outputHandler = outputHandler;
        this.resolver = resolver;
        this.entryNode = new EntryNode<T>(resolver);
        this.exitNode = new ExitNode<T>(resolver, outputHandler);
    }

    public ProcessingGraph(IProcessingGraphFactory<T> factory){
        super();
        this.outputHandler = factory.getOutputHandler();
        this.entryNode = factory.getEntryNode();
        this.exitNode = factory.getExitNode();
        this.resolver = factory.getResourceResolver();
    }

    public EntryNode<T> getEntryNode(){
        return this.entryNode;
    }

    public ExitNode<T> getExitNode(){
        return this.exitNode;
    }

    public int getInputCount(){
        return this.entryNode.getOutputKeys().size();
    }

    public void setInputCount(int count){
        this.entryNode = new EntryNode<T>(count, this.resolver); 
    }

    public int getOutputCount(){
        return this.exitNode.getInputKeys().size();
    }

    public void setOutputCount(int count){
        this.exitNode = new ExitNode<T>(count, this.resolver, this.outputHandler); 
    }

    public void startProcessing( HashMap<String, T> inputs) throws IncompleteGraphException {
        if( !this.isComplete() ){
            throw new IncompleteGraphException("The graph inputs and outputs are not hooked up correctly.");                 
        }

        for(String entryKey : this.entryNode.getOutputKeys()){
            if( !inputs.containsKey(entryKey) ){
                throw new IncompleteGraphException("The provided inputs do not match the graph inputs.");
            }
        }

        for(String entryKey : this.entryNode.getOutputKeys()){
            this.entryNode.graphInputReady(entryKey, inputs.get(entryKey));
        }
    }
    
}
