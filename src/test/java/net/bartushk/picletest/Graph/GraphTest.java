package net.bartushk.picletest.Graph;

import org.junit.Test;
import static org.junit.Assert.*;

import net.bartushk.picle.Graph.Graph;
import net.bartushk.picle.Graph.Node;
import net.bartushk.picle.Graph.Exceptions.BadNodeException;

public class GraphTest
{
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

}
