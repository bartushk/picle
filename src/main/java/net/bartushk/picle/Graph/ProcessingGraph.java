package net.bartushk.picle.Graph;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.bartushk.picle.Combiner.*;
import net.bartushk.picle.Core.*;
import net.bartushk.picle.Filter.*;
import net.bartushk.picle.Splitter.*;

public class ProcessingGraph<T> extends Graph 
{
    private EntryNode<T> entryNode; 
    private ExitNode<T> exitNode;
    private ExecutorService executor;
    private IResourceResolver<T> resourceResolver;
    private IGraphOutputHandler<T> outputHandler;

    public ProcessingGraph(){
        this( new DictionaryResourceResolver<T>(), Executors.newFixedThreadPool(5), new LoggingOutputHandler<T>());
    }

    public ProcessingGraph(IResourceResolver<T> resolver, ExecutorService executor, IGraphOutputHandler<T> outputHandler){
        super();
        this.executor = executor;
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

    public void startGraph( HashMap<String, T> inputs){

    }

    public void createNode(IFilter filter){
                
    }

    public void createNode(ICombiner combiner){

    }

    public void createNode(ISplitter splitter){

    }
    
}
