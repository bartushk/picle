package net.bartushk.picle.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import net.bartushk.picle.Graph.Exceptions.*;

/**
 *
 * Basic graph class that manages all of its nodes by name.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class Graph
{
    protected HashMap<String, Node> nodes;   
    private Collection<Edge> edges;

    public Graph(){
        this.nodes = new HashMap<String, Node>();
        this.edges = new ArrayList<Edge>();
    }

    public void addNode(Node node) throws BadNodeException {
        if( node == null )
            throw new BadNodeException("Node cannot be null.");
        if( nodes.containsKey(node.nodeKey) )
            throw new BadNodeException("Node already exists.");
        this.nodes.put(node.getNodeKey(), node);
    }

    public void addNode(String nodeKey) throws BadNodeException {
        if( nodes.containsKey(nodeKey) )
            throw new BadNodeException("Node already exists.");
        this.nodes.put(nodeKey, new Node(nodeKey) ); 
    }

    public void linkNodes(String fromNode, String toNode,
                          String fromKey, String toKey) throws InvalidNodeLinkException
    {
        if( !nodes.containsKey(fromNode) )       
            throw new InvalidNodeLinkException("fromNode does not exist.");
            
        if( !nodes.containsKey(toNode) )       
            throw new InvalidNodeLinkException("toNode does not exist.");

        if( fromNode == toNode )       
            throw new InvalidNodeLinkException("Cannot make a link to the same node.");

        Node fNode = nodes.get(fromNode);
        Node tNode = nodes.get(toNode);

        if( !fNode.getOutputKeys().contains(fromKey) )
            throw new InvalidNodeLinkException("fromNode does not contain an output for fromKey");

        if( !tNode.getOutputKeys().contains(toKey) )
            throw new InvalidNodeLinkException("toNode does not contain an output for toKey");

        for(Edge edge: fNode.getFromEdges()){
            if( edge.getFromKey() == fromKey )
                throw new InvalidNodeLinkException("fromNode already has a valid edge at that output.");
        }

        for(Edge edge: tNode.getFromEdges()){
            if( edge.getToKey() == toKey )
                throw new InvalidNodeLinkException("toNode already has a valid edge at that input.");
        }

        Edge newEdge = new Edge(fNode, tNode, fromKey, toKey);
        this.edges.add(newEdge);
        fNode.addFromEdge(newEdge);
        tNode.addToEdge(newEdge);
    }

    public Collection<Edge> getEdges(){
        return this.edges;
    }

    public Collection<Node> getNodes(){
        return this.nodes.values();
    }
}
