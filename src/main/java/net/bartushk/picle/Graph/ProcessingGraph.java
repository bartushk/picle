package net.bartushk.picle.Graph;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import net.bartushk.picle.Core.DictionaryResourceResolver;
import net.bartushk.picle.Core.IResourceResolver;

public class ProcessingGraph<T> extends Graph
{
    private ProcessingNode<T> entryNode; 
    private ProcessingNode<T> exitNode;
    private ExecutorService executor;
    private IResourceResolver<T> resourceResolver;

    public ProcessingGraph(){
        this( new DictionaryResourceResolver<T>(), Executors.newFixedThreadPool(5));
    }
    

    public ProcessingGraph(IResourceResolver<T> resolver, ExecutorService executor){
        super();
        this.executor = executor;
        this.resourceResolver = resolver;
    }

}
