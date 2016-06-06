package net.bartushk.picletest.Graph;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bartushk.picle.Graph.Edge;
import net.bartushk.picle.Graph.Graph;
import net.bartushk.picle.Graph.Node;
import net.bartushk.picle.Graph.Exceptions.BadNodeException;
import net.bartushk.picle.Graph.Exceptions.InvalidNodeLinkException;

public class GraphTest
{
    /**
     * Helper function used for creating an initial graph for testing.
     */
    private Graph initGraph(){
        Graph returnGraph = new Graph();
        // make some mock nodes with 3 inputs and outputs
        List<String> nodeInput = new ArrayList<String>();
        List<String> nodeOutput = new ArrayList<String>();
        nodeInput.add("Input1");
        nodeInput.add("Input2");
        nodeInput.add("Input3");
        nodeOutput.add("Output1");
        nodeOutput.add("Output2");
        nodeOutput.add("Output3");

        Node node1 = (Node)mock(Node.class);
        Node node2 = (Node)mock(Node.class);
        when(node1.getNodeKey()).thenReturn("TestOne");
        when(node2.getNodeKey()).thenReturn("TestTwo");
        when(node1.getInputKeys()).thenReturn(nodeInput);
        when(node2.getInputKeys()).thenReturn(nodeInput);
        when(node1.getOutputKeys()).thenReturn(nodeOutput);
        when(node2.getOutputKeys()).thenReturn(nodeOutput);

        // Create some artifical edges between the two mock nodes.
        Edge linkedEdge1 = new Edge(node1, node2, "Output2", "Bad");
        Edge linkedEdge2 = new Edge(node1, node2, "Bad", "Input2");
        Edge linkedEdge3 = new Edge(node1, node2, "Output3", "Input3");
        Collection<Edge> edges = new ArrayList<Edge>();
        edges.add(linkedEdge1);
        edges.add(linkedEdge2);
        edges.add(linkedEdge3);
        when(node1.getFromEdges()).thenReturn(edges);
        when(node2.getToEdges()).thenReturn(edges);
        returnGraph.getEdges().add(linkedEdge1);
        returnGraph.getEdges().add(linkedEdge2);
        returnGraph.getEdges().add(linkedEdge3);

        try{
            returnGraph.addNode(node1);
            returnGraph.addNode(node2);
        } catch(BadNodeException e){
            returnGraph = new Graph();
        }

        return returnGraph;
    }

    private void addNode(Graph graph, Node node){
        try{
            graph.addNode(node);
        } catch( BadNodeException e ){
            assertNull(e);
        }
    }

    private void addNode(Graph graph, String nodeName){
        try{
            graph.addNode(nodeName);
        } catch( BadNodeException e ){
            assertNull(e);
        }
    }
    
    @Test
    public void Graph_Construction(){
        Graph testGraph = new Graph();     
        assertNotNull(testGraph);
    }

    @Test
    public void Graph_AddNode(){
        Graph testGraph = new Graph();
        Node toAdd = new Node(); 
        addNode(testGraph, toAdd);
        assertTrue(testGraph.getNodes().contains(toAdd));
    }

    @Test
    public void Graph_AddNullNode(){
        Graph testGraph = new Graph(); 
        Node toAdd = null;
        Exception ex = null;
        try{
            testGraph.addNode(toAdd);
        } catch( BadNodeException e ){
            ex = e; 
        }
        assertNotNull(ex);
    }

    @Test
    public void Graph_AddDuplicatNode(){
        Graph testGraph = new Graph();
        Node toAddOne = new Node();
        Node toAddTwo = new Node();
        Exception ex = null;
        addNode(testGraph, toAddOne);

        try{
            testGraph.addNode(toAddTwo);
        } catch( BadNodeException e ){
            ex = e;
        }
        assertNotNull(ex);
    }

    @Test
    public void Graph_RemoveNode(){
        Graph testGraph = new Graph();
        Node toAddOne = new Node();
        Node toAddTwo = new Node("Node Two");

        try{
            testGraph.addNode(toAddOne);
            testGraph.addNode(toAddTwo);
        } catch (Exception e){
            assertNull(e);
        }
        // no error on non existant node.
        testGraph.removeNode("asdf");
        // remove second node
        testGraph.removeNode("Node Two");

        Collection<Node> nodes = testGraph.getNodes();
        assertFalse(nodes.contains(toAddTwo));
        assertTrue(nodes.contains(toAddOne));
    }

    @Test
    public void Graph_AddNodeKey(){
        Graph testGraph = new Graph();
        addNode(testGraph, "testNode");
        Node addedNode = (Node)testGraph.getNodes().toArray()[0];
        assertEquals(addedNode.getNodeKey(), "testNode");
    }

    @Test
    public void Graph_AddNodeKeyDuplicate(){
        Graph testGraph = new Graph();
        addNode(testGraph, "testNode");

        Exception ex = null;
        try{
            testGraph.addNode("testNode");
        } catch( BadNodeException e){
            ex = e;
        }
        assertNotNull(ex);
    }

    @Test
    public void Graph_linkNodes_BadNodes(){
        Graph testGraph = initGraph();
        Exception ex = null;
        // Non existant toNode should error.
        try{
            testGraph.linkNodes("TestOne", "TestBad", "Output1", "Input1");
        } catch (InvalidNodeLinkException e){
            ex = e;
        }
        assertEquals(ex.getMessage(), "toNode does not exist.");
        ex = null;

        // Non existant fromNode should error.
        try{
            testGraph.linkNodes("TestBad", "TestOne", "Output1", "Input1");
        } catch (InvalidNodeLinkException e){
            ex = e;
        }
        assertEquals(ex.getMessage(), "fromNode does not exist.");
        ex = null;

        // Nodes of the same name should error.
        try{
            testGraph.linkNodes("TestOne", "TestOne", "Output1", "Input1");
        } catch (InvalidNodeLinkException e){
            ex = e;
        }
        assertEquals(ex.getMessage(), "Cannot make a link to the same node.");
    }

    @Test
    public void Graph_linkNodes_BadEdge(){
        Graph testGraph = initGraph();
        Exception ex = null;
        // Non existant input should error.
        try{
            testGraph.linkNodes("TestOne", "TestTwo", "Output1", "InputBad");
        } catch (InvalidNodeLinkException e){
            ex = e;
        }
        assertEquals(ex.getMessage(), "toNode does not contain the specified input.");
        ex = null;
        
        // Non existant output should error.
        try{
            testGraph.linkNodes("TestOne", "TestTwo", "OutputBad", "Input1");
        } catch (InvalidNodeLinkException e){
            ex = e;
        }
        assertEquals(ex.getMessage(), "fromNode does not contain the specified output.");
        ex = null;

        // Edge already esists on fromNode should error.
        try{
            testGraph.linkNodes("TestOne", "TestTwo", "Output2", "Input1");
        } catch (InvalidNodeLinkException e){
            ex = e;
        }
        assertEquals(ex.getMessage(), "fromNode already has a valid edge at that output.");
        ex = null;

        // Edge already esists on toNode should error.
        try{
            testGraph.linkNodes("TestOne", "TestTwo", "Output1", "Input2");
        } catch (InvalidNodeLinkException e){
            ex = e;
        }
        assertEquals(ex.getMessage(), "toNode already has a valid edge at that input.");

    }
    
    
}
