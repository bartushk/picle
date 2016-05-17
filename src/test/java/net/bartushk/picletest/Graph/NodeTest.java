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
    public void Node_FromEdge(){
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
    public void Node_ToEdge(){
        Node node = new Node(); 
        Edge testEdge1 = new Edge(null, node, "out1", "in1");
        Edge testEdge2 = new Edge(null, node, "out2", "in2");
        Edge testEdge3 = new Edge(null, node, "out3", "in3");
        node.addToEdge(testEdge1);
        node.addToEdge(testEdge2);
        node.addToEdge(testEdge3);

        Collection<Edge> fromEdges = node.getToEdges();
        assertTrue(fromEdges.contains(testEdge1));
        assertTrue(fromEdges.contains(testEdge2));
        assertTrue(fromEdges.contains(testEdge3));
        assertEquals(fromEdges.size(), 3);
    }

    @Test
    public void Node_InputOutputKeys(){
        Node node = new Node();
        assertEquals(node.getInputKeys().size(), 1);
        assertEquals(node.getInputKeys().toArray()[0], "input");
        assertEquals(node.getOutputKeys().size(), 1);
        assertEquals(node.getOutputKeys().toArray()[0], "output");
    }
}
