package net.bartushk.picle.Graph;

import java.util.HashMap;

import net.bartushk.picle.Core.*;
import net.bartushk.picle.Graph.Exceptions.IncompleteGraphException;

public class ProcessingGraph<T> extends Graph 
{
    private EntryNode<T> entryNode; 
    private ExitNode<T> exitNode;
    private IResourceResolver<T> resourceResolver;
    private IGraphOutputHandler<T> outputHandler;

    public ProcessingGraph(){
        this( new DictionaryResourceResolver<T>(), new LoggingOutputHandler<T>());
    }

    public ProcessingGraph(IResourceResolver<T> resolver, IGraphOutputHandler<T> outputHandler){
        super();
        this.resourceResolver = resolver;
        this.outputHandler = outputHandler;
        this.entryNode = new EntryNode<T>(resolver);
        this.exitNode = new ExitNode<T>(resolver, outputHandler);
    }

    public void setInputCount(int count){
        this.entryNode = new EntryNode<T>(count, this.resourceResolver); 
    }

    public void setOutputCount(int count){
        this.exitNode = new ExitNode<T>(count, this.resourceResolver, this.outputHandler); 
    }

    public void startProcessing( HashMap<String, T> inputs) throws IncompleteGraphException {
        if( this.isComplete() ){
            throw new IncompleteGraphException("The graph inputs and outputs are not hooked up correctly.");                 
        }
        for(String entryKey : this.entryNode.getInputKeys()){
            if( inputs.containsKey(entryKey) ){
                throw new IncompleteGraphException("The provided inputs do not match the graph inputs.");
            }
        }

        for(String entryKey : this.entryNode.getInputKeys()){
            this.entryNode.graphInputReady(entryKey, inputs.get(entryKey));
        }
    }
    
}
