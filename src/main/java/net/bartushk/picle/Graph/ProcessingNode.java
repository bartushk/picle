package net.bartushk.picle.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    private List<String> inputKeys;
    private List<String> outputKeys;
    private HashMap<String, String> inputLookup;
    private IOperation<T> operation;
    private IResourceResolver<T> resourceResolver;
    
    public ProcessingNode(IOperation<T> operation, IResourceResolver<T> resolver){
        super();
        this.receivedInputs = 0;
        this.operation = operation;
        this.resourceResolver = resourceResolver;
        this.inputKeys = operation.getInputKeys();
        this.outputKeys = operation.getOutputKeys();
    }

    public void Reset(){
        this.receivedInputs = 0;
    }

    public void InputReady(String inputName, String lookupKey){
        synchronized(this) {
            receivedInputs++; 
            this.inputLookup.put(inputName, lookupKey);
            if( receivedInputs >= inputKeys.size() ){
                new Thread(this).start();
            }
        }
    }

    public List<String> getInputKeys(){
        return inputKeys;
    }

    public List<String> getOutputKeys(){
        return outputKeys;
    }


    public void run(){
        HashMap<String, T> operationInput = new HashMap<String, T>(); 
        for(String key : this.inputKeys){
            operationInput.put(key, this.resourceResolver.getResource(this.inputLookup.get(key)));
        }
        HashMap<String, T> operationOutput = operation.process(operationInput);
        for(String key : this.outputKeys){
            String dataKey = this.nodeKey + key;
            this.resourceResolver.putResource(dataKey, operationOutput.get(key)); 
            Edge activeEdge = this.fromEdges.get(key);
            @SuppressWarnings("unchecked")
            ProcessingNode<T> toTrigger = (ProcessingNode<T>)activeEdge.getToNode();       
            toTrigger.InputReady(activeEdge.getToKey(), dataKey);
        }
    }
}
