package net.bartushk.picle.Graph;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import net.bartushk.picle.Core.DictionaryResourceResolver;
import net.bartushk.picle.Core.IResourceResolver;

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
    
}
