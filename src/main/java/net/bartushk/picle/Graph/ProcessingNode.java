package net.bartushk.picle.Graph;

import java.io.Console;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;

import net.bartushk.picle.Core.IOperation;
import net.bartushk.picle.Core.IResourceResolver;

/**
 *
 * A processing node has a set of input keys and output keys. Once it has received
 * all of its inputs, it runs a particular operation, and then passes the results on 
 * to the next processing node. 
 *
 * @author Kyle Bartush
 * @see 
 * @since 0.1
 */
public class ProcessingNode<T> extends Node implements Runnable
{
    /*
     * An internal counter of the ammount of inputs received so far.
     * This is used so that once all the necessary inputs are recieved,
     * the operation for this node is run.
     */
    private int receivedInputs;
    private HashMap<String, String> inputLookup;
    private HashMap<String, Integer> inputCounts;
    private IOperation<T> operation;
    private IResourceResolver<T> resourceResolver;
    private AbstractExecutorService executor;
    
    public ProcessingNode(String nodeKey, IOperation<T> operation, 
                IResourceResolver<T> resolver, AbstractExecutorService executor){
        super(nodeKey);
        this.operation = operation;
        this.resourceResolver = resolver;
        this.executor = executor;
        this.Reset();
    }

    public void Reset(){
        this.receivedInputs = 0;
        this.inputLookup = new HashMap<String, String>();
        this.inputCounts = new HashMap<String, Integer>();
        for( String input : this.operation.getInputKeys() ){
            this.inputCounts.put(input, 0);
        }
    }

    public void InputReady(String inputName, String lookupKey){
        if( !inputCounts.containsKey(inputName) )
            return;
        synchronized(this) {
            if( inputCounts.get(inputName) > 0 )
                return;
            inputCounts.put(inputName, 1);
            receivedInputs++; 
            this.inputLookup.put(inputName, lookupKey);
            if( receivedInputs >= operation.getInputKeys().size() ){
                executor.execute(this);
            }
        }
    }

    public void run(){
        HashMap<String, T> operationInput = new HashMap<String, T>(); 
        for( String key : this.operation.getInputKeys() ){
            operationInput.put(key, this.resourceResolver.getResource(this.inputLookup.get(key)));
        }
        HashMap<String, T> operationOutput = operation.process(operationInput);
        for( String key : this.operation.getOutputKeys() ){
            String dataKey = this.nodeKey + key;
            this.resourceResolver.putResource(dataKey, operationOutput.get(key)); 
            Edge activeEdge = this.fromEdges.get(key);
            @SuppressWarnings("unchecked")
            ProcessingNode<T> toTrigger = (ProcessingNode<T>)activeEdge.getToNode();       
            toTrigger.InputReady(activeEdge.getToKey(), dataKey);
        }
    }

    @Override
    public List<String> getInputKeys(){
        return operation.getInputKeys();
    }

    @Override
    public List<String> getOutputKeys(){
        return operation.getOutputKeys();
    }

}
