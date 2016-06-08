package net.bartushk.picle.Graph;

import java.util.ArrayList;
import java.util.List;

import net.bartushk.picle.Core.IResourceResolver;


/**
 *
 * Node for the exit point from a processing graph. It only has inputs, and 
 * its inputs are matched with the number of outputs that the processing graph
 * has. It allows for a output handler to be used that is triggered when it
 * receives one of the graph results.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class ExitNode<T> extends Node
{
    private IResourceResolver<T> resolver;
    private IGraphOutputHandler<T> outputHandler;

    public ExitNode(IResourceResolver<T> resolver, IGraphOutputHandler<T> outputHandler){
        this(1, resolver, outputHandler);
    }

    public ExitNode(int numOutputs, IResourceResolver<T> resolver, IGraphOutputHandler<T> outputHandler){
        super("Exit-Node");
        for(int i = 0; i < numOutputs; i++){
            this.outputKeys.add("Input" + i); 
        }
        this.resolver = resolver;
        this.outputHandler = outputHandler;
    }

    @Override
    public void InputReady(String inputName, String lookupKey){
        T data = this.resolver.getResource(lookupKey); 
        this.outputHandler.handleOutput(inputName, data);
    }

    @Override
    public List<String> getOutputKeys(){
        return new ArrayList<String>();
    }
}
