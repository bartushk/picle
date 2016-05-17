package net.bartushk.picle.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Class modeling a basic graph node.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class Node
{
    protected HashMap<String, Edge> fromEdges;
    protected HashMap<String, Edge> toEdges;
    protected String nodeKey;
    protected List<String> inputKeys;
    protected List<String> outputKeys;

    public Node(){
        nodeKey = "No-Name-Node";
        fromEdges = new HashMap<String, Edge>();
        toEdges = new HashMap<String, Edge>();
        inputKeys = new ArrayList<String>();
        inputKeys.add("input");
        outputKeys = new ArrayList<String>();
        outputKeys.add("output");
    }

    public Node(String nodeKey){
        this.nodeKey = nodeKey;
    }

    public void addFromEdge(Edge toAdd){
        this.fromEdges.put(toAdd.getFromKey(), toAdd);
    }

    public void addToEdge(Edge toAdd){
        this.toEdges.put(toAdd.getToKey(), toAdd);
    }
    
    public Collection<Edge> getFromEdges(){
        return fromEdges.values();
    }

    public Collection<Edge> getToEdges(){
        return toEdges.values();
    }

    public String getNodeKey(){
        return nodeKey;
    }
    
    public void setNodeKey(String nodeKey){
        this.nodeKey = nodeKey;
    }

    public List<String> getInputKeys(){
        return inputKeys;
    }

    public List<String> getOutputKeys(){
        return outputKeys;
    }
}