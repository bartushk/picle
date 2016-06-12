package net.bartushk.picletest.Graph;

import org.junit.Test;

import net.bartushk.picle.Graph.Edge;
import net.bartushk.picle.Graph.Node;
import static org.junit.Assert.*;

import java.util.Collection;

public class NodeTest
{
    @Test 
    public void Node_DefaultConstructor(){
        Node node = new Node();
        assertNotNull(node);
    }

    @Test
    public void Node_StringConstructor(){
        Node node = new Node("Test");
        assertEquals(node.getNodeKey(), "Test");
    }

    @Test
    public void Node_AddFromEdge(){
        Node node = new Node(); 
        Edge testEdge1 = new Edge(node, null, "out1", "");
        Edge testEdge2 = new Edge(node, null, "out2", "");
        Edge testEdge3 = new Edge(node, null, "out3", "");
        node.addFromEdge(testEdge1);
        node.addFromEdge(testEdge2);
        node.addFromEdge(testEdge3);

        Collection<Edge> fromEdges = node.getFromEdges();
        assertTrue(fromEdges.contains(testEdge1));
        assertTrue(fromEdges.contains(testEdge2));
        assertTrue(fromEdges.contains(testEdge3));
        assertEquals(fromEdges.size(), 3);
    }

    @Test
    public void Node_RemoveFromEdge(){
        Node node = new Node();
        Edge testEdge1 = new Edge(node, null, "out1", "");
        Edge testEdge2 = new Edge(node, null, "out2", "");
        Edge testEdge3 = new Edge(node, null, "out3", "");
        node.addFromEdge(testEdge1);
        node.addFromEdge(testEdge2);
        node.addFromEdge(testEdge3);
        
        node.removeFromEdge(testEdge1);

        Collection<Edge> fromEdges = node.getFromEdges();
        assertFalse(fromEdges.contains(testEdge1));
        assertTrue(fromEdges.contains(testEdge2));
        assertTrue(fromEdges.contains(testEdge3));
        assertEquals(fromEdges.size(), 2);
    }

    @Test
    public void Node_AddToEdge(){
        Node node = new Node(); 
        Edge testEdge1 = new Edge(null, node, "out1", "in1");
        Edge testEdge2 = new Edge(null, node, "out2", "in2");
        Edge testEdge3 = new Edge(null, node, "out3", "in3");
        node.addToEdge(testEdge1);
        node.addToEdge(testEdge2);
        node.addToEdge(testEdge3);

        Collection<Edge> toEdges = node.getToEdges();
        assertTrue(toEdges.contains(testEdge1));
        assertTrue(toEdges.contains(testEdge2));
        assertTrue(toEdges.contains(testEdge3));
        assertEquals(toEdges.size(), 3);
    }

    @Test
    public void Node_RemoveToEdge(){
        Node node = new Node();
        Edge testEdge1 = new Edge(null, node, "out1", "in1");
        Edge testEdge2 = new Edge(null, node, "out2", "in2");
        Edge testEdge3 = new Edge(null, node, "out3", "in3");
        node.addToEdge(testEdge1);
        node.addToEdge(testEdge2);
        node.addToEdge(testEdge3);
        
        node.removeToEdge(testEdge1);

        Collection<Edge> toEdges = node.getToEdges();
        assertFalse(toEdges.contains(testEdge1));
        assertTrue(toEdges.contains(testEdge2));
        assertTrue(toEdges.contains(testEdge3));
        assertEquals(toEdges.size(), 2);
    }

    @Test
    public void Node_InputOutputKeys(){
        Node node = new Node();
        assertEquals(node.getInputKeys().size(), 0);
        assertEquals(node.getOutputKeys().size(), 0);
    }
}
