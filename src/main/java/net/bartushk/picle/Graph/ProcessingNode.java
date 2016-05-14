package net.bartushk.picle.Graph;

import java.util.ArrayList;

import net.bartushk.picle.Core.IOperation;

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
    private ArrayList<String> inputKeys;
    private ArrayList<String> outputKeys;
    private String nodeKey;
    private IOperation<T> operation;
    
    public ProcessingNode(IOperation<T> operation){
        super();
        this.receivedInputs = 0;
        this.operation = operation;
    }

    public void Reset(){
        this.receivedInputs = 0;
    }

    public void InputReady(String key){
        synchronized(this) {
            receivedInputs++; 
            if( receivedInputs >= inputKeys.size() ){
                new Thread(this).start();
            }
        }
    }

    public ArrayList<String> getInputKeys(){
        return inputKeys;
    }

    public ArrayList<String> getOutputKeys(){
        return outputKeys;
    }

    public String getNodeKey(){
        return nodeKey;
    }
    
    public void setNodeKey(String nodeKey){
        this.nodeKey = nodeKey;
    }

    public void run(){
        
    }
}
