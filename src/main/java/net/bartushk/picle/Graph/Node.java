package net.bartushk.picle.Graph;

import java.util.Collection;
import java.util.HashMap;

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

    public Node(){
            nodeKey = "No-Name-Node";
    }

    public Node(String nodeKey){
        this.nodeKey = nodeKey;
    }

    public void addFromEdge(Edge toAdd){
        this.toEdges.put(toAdd.getToKey(), toAdd);
    }

    public void addToEdge(Edge toAdd){
        this.fromEdges.put(toAdd.getFromKey(), toAdd);
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
}
