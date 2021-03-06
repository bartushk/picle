package net.bartushk.picle.Graph;

import java.util.ArrayList;
import java.util.List;

import net.bartushk.picle.Core.IResourceResolver;

/**
 *
 * Node for the entry point into a processing graph. It only has outputs,
 * and its outputs are matched with the number of inputs necessary for the
 * processing graph. It can also 'manually' have it's outputs triggered as complete.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class EntryNode<T> extends Node
{
    private IResourceResolver<T> resolver;

    public EntryNode(IResourceResolver<T> resolver){
        this(1, resolver);
    }
    
    public EntryNode(int numInputs, IResourceResolver<T> resolver){
        super("Entry-Node"); 
        for(int i = 1; i <= numInputs; i++){
            this.outputKeys.add("Input" + i); 
        }
        this.resolver = resolver;
    }

    public void graphInputReady(String itemKey, T data){
        if(!this.getOutputKeys().contains(itemKey)){
            String exString = String.format("Input key of '' is not valid for this exit node.", itemKey); 
            throw new IllegalArgumentException(exString);
        }
        String dataKey = this.nodeKey + itemKey;
        this.resolver.putResource(dataKey, data);
        Edge activeEdge = this.fromEdges.get(itemKey);
        if( activeEdge == null ){
            String exString = String.format("No edge found for itemKey '%1'.", itemKey); 
            throw new IllegalArgumentException(exString);
        }
        Node toTrigger = activeEdge.getToNode();       
        toTrigger.InputReady(activeEdge.getToKey(), dataKey);
    }

    @Override
    public List<String> getInputKeys(){
        return new ArrayList<String>();
    }
}
