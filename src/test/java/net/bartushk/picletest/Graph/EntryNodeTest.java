package net.bartushk.picletest.Graph;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import net.bartushk.picle.Core.IResourceResolver;
import net.bartushk.picle.Graph.Edge;
import net.bartushk.picle.Graph.EntryNode;
import net.bartushk.picle.Graph.Node;


public class EntryNodeTest
{

    private class MockEntryNode{
        public EntryNode<String> node;
        public IResourceResolver<String> resolver;
        public Node toNode;
        public Edge edge1;
        public Edge edge2;
        public Edge edge3;
    }

    /**
     *
     * Constructs a fully mocked entry node of type string with access to each item that was
     * mocked to create it.
     */
    private MockEntryNode  getMockNode(){
        MockEntryNode mockNode = new MockEntryNode();
        IResourceResolver<String> res = (IResourceResolver<String>)mock(IResourceResolver.class);
        EntryNode<String> node = new EntryNode<String>(3, res);
        Node toNode = mock(Node.class);
        Edge mockEdge1 = mock(Edge.class);
        when(mockEdge1.getFromKey()).thenReturn("Input1");
        when(mockEdge1.getToKey()).thenReturn("Output1");
        when(mockEdge1.getToNode()).thenReturn(toNode);
        node.addFromEdge(mockEdge1);
        Edge mockEdge2 = mock(Edge.class);
        when(mockEdge2.getFromKey()).thenReturn("Input2");
        when(mockEdge2.getToKey()).thenReturn("Output2");
        when(mockEdge2.getToNode()).thenReturn(toNode);
        node.addFromEdge(mockEdge2);
        Edge mockEdge3 = mock(Edge.class);
        when(mockEdge3.getFromKey()).thenReturn("Input3");
        when(mockEdge3.getToKey()).thenReturn("Output3");
        when(mockEdge3.getToNode()).thenReturn(toNode);
        node.addFromEdge(mockEdge3);

        mockNode.resolver = res;
        mockNode.node = node;
        mockNode.edge1 = mockEdge1;
        mockNode.edge2 = mockEdge2;
        mockNode.edge3 = mockEdge3;
        mockNode.toNode = toNode;

        return mockNode;
    }

    @Test
    public void EntryNode_DefaultConstructor(){
        EntryNode<String> node = new EntryNode<String>(mock(IResourceResolver.class));
        assertNotNull(node);
        assertEquals(node.getOutputKeys().size(), 1);
    }

    @Test
    public void EntryNode_NumOutputsConstructor(){
        EntryNode<String> node = new EntryNode<String>(2, mock(IResourceResolver.class));
        assertNotNull(node);
        assertEquals(node.getOutputKeys().size(), 2);
    }

    @Test
    public void EntryNode_graphInputReady_BadKey(){
        MockEntryNode mockNode = getMockNode(); 

        Exception ex = null;
        try{
            mockNode.node.graphInputReady("bad_key", "hello");
        } catch(IllegalArgumentException e){
            ex = e;
        }

        assertNotNull(ex);
    }
    
    @Test
    public void EntryNode_graphInputReady_GoodKeys(){
        MockEntryNode mockNode = getMockNode();

        Exception ex = null;
        try{
            mockNode.node.graphInputReady("Input1", "i1");
            mockNode.node.graphInputReady("Input2", "i2");
            mockNode.node.graphInputReady("Input3", "i3");
        } catch(Exception e){
            ex = e;
        }

        assertNull(ex);
        verify(mockNode.resolver, times(1)).putResource(mockNode.node.getNodeKey() + "Input1", "i1");
        verify(mockNode.resolver, times(1)).putResource(mockNode.node.getNodeKey() + "Input2", "i2");
        verify(mockNode.resolver, times(1)).putResource(mockNode.node.getNodeKey() + "Input3", "i3");
        verify(mockNode.toNode, times(1)).InputReady("Output1", mockNode.node.getNodeKey() + "Input1"); 
        verify(mockNode.toNode, times(1)).InputReady("Output2", mockNode.node.getNodeKey() + "Input2"); 
        verify(mockNode.toNode, times(1)).InputReady("Output3", mockNode.node.getNodeKey() + "Input3"); 
    }

}
