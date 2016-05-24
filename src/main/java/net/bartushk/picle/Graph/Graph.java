package net.bartushk.picle.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import net.bartushk.picle.Graph.Exceptions.*;

/**
 *
 * Basic graph class that manages all of its nodes.
 *
 * @author Kyle Bartush
 * @version 0.1
 * @since 2016-5-21
 */
public class Graph
{
    protected HashMap<String, Node> nodes;   
    private Collection<Edge> edges;

    public Graph(){
        this.nodes = new HashMap<String, Node>();
        this.edges = new ArrayList<Edge>();
    }

    /**
     *
     * Adds a node to the graph.
     *
     * @param node The ndoe to be added
     * @throws BadNodeException if the node is null, or has a node key
     *         that the graph already contains.
     */
    public void addNode(Node node) throws BadNodeException {
        if( node == null )
            throw new BadNodeException("Node cannot be null.");
        if( nodes.containsKey(node.nodeKey) )
            throw new BadNodeException("Node already exists.");
        this.nodes.put(node.getNodeKey(), node);
    }

    /**
     *
     * Creates and adds a node to the graph with the specified key.
     *
     * @param nodeKey Name for the node to be created and added to the graph.
     * @throws BadNodeException if the nodeKey already exists in the graph.
     */
    public void addNode(String nodeKey) throws BadNodeException {
        if( nodes.containsKey(nodeKey) )
            throw new BadNodeException("Node already exists.");
        this.nodes.put(nodeKey, new Node(nodeKey) ); 
    }

    /**
     *
     * Removes a node from the graph based on the specified key. If a node
     * with that key does not exist, nothing is done.
     *
     * @param nodeKey Node to be removed from the graph.
     */
    public void removeNode(String nodeKey) {
        if( nodes.containsKey(nodeKey) ){
            this.nodes.remove(nodeKey);
        }
    }

    /**
     *
     * Creates a link between two nodes in the graph. It does this by creating a directed edge
     * between the two nodes. It must connect one of the 'fromNode' outputs to one of the
     * to 'toNode' inputs.
     *
     * @param fromNode Node that the link will come from. (Tail of the edge)
     * @param toNode Node that the link will go to. (Head of the edge)
     * @param fromOutput Node output the link is created from
     * @param toInput Node input the link is created to 
     * @throws InvalidNodeException if for any reason this link cannot be created.
     */
    public void linkNodes(String fromNode, String toNode,
                          String fromOutput, String toInput) throws InvalidNodeLinkException
    {
        if( !nodes.containsKey(fromNode) )       
            throw new InvalidNodeLinkException("fromNode does not exist.");
            
        if( !nodes.containsKey(toNode) )       
            throw new InvalidNodeLinkException("toNode does not exist.");

        if( fromNode == toNode )       
            throw new InvalidNodeLinkException("Cannot make a link to the same node.");

        Node fNode = nodes.get(fromNode);
        Node tNode = nodes.get(toNode);

        if( !fNode.getOutputKeys().contains(fromOutput) )
            throw new InvalidNodeLinkException("fromNode does not contain an output for fromOutput");

        if( !tNode.getOutputKeys().contains(toInput) )
            throw new InvalidNodeLinkException("toNode does not contain an input for toInput");

        for(Edge edge: fNode.getFromEdges()){
            if( edge.getFromKey() == fromOutput )
                throw new InvalidNodeLinkException("fromNode already has a valid edge at that output.");
        }

        for(Edge edge: tNode.getFromEdges()){
            if( edge.getToKey() == toInput )
                throw new InvalidNodeLinkException("toNode already has a valid edge at that input.");
        }

        Edge newEdge = new Edge(fNode, tNode, fromOutput, toInput);
        this.edges.add(newEdge);
        fNode.addFromEdge(newEdge);
        tNode.addToEdge(newEdge);
    }

    /**
     *
     * Removes the an edge between two nodes. If the link does not exist, nothing happens.
     *
     * @param fromNode Node that the removed edge comes from. (Tail of the edge)
     * @param toNode Node that the removed edge goes to. (Head of the edge)
     * @param fromOutput Node output the removed edge comes from.
     * @param toInput Node input the the removed edge goes to.
     * @throws InvalidNodeException if for any reason this link cannot be created.
     */
    public void unLinkNodes(String fromNode, String toNode,
                            String fromOutput, String toInput) 
    {
        


    }

    public Collection<Edge> getEdges(){
        return this.edges;
    }

    public Collection<Node> getNodes(){
        return this.nodes.values();
    }
}
