package net.bartushk.picletest.Graph;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import net.bartushk.picle.Core.IResourceResolver;
import net.bartushk.picle.Graph.ExitNode;
import net.bartushk.picle.Graph.IGraphOutputHandler;


public class ExitNodeTest
{

    private class MockExitNode {
        public ExitNode<String> node;
        public IResourceResolver<String> resolver;
        public IGraphOutputHandler<String> handler;
    }

    private MockExitNode getMockNode(){
        MockExitNode mockNode = new MockExitNode();
        IResourceResolver<String> res = (IResourceResolver<String>)mock(IResourceResolver.class);
        IGraphOutputHandler<String> handler = (IGraphOutputHandler<String>)mock(IGraphOutputHandler.class);
        when(res.getResource("i1")).thenReturn("i1");
        when(res.getResource("i2")).thenReturn("i2");
        when(res.getResource("i3")).thenReturn("i3");

        ExitNode<String> node = new ExitNode<String>(3, res, handler);

        mockNode.node = node;
        mockNode.resolver = res;
        mockNode.handler = handler;

        return mockNode;
    }

    @Test
    public void ExitNode_DefaultConstructor(){
        ExitNode<String> node = new ExitNode<String>(
                    mock(IResourceResolver.class), mock(IGraphOutputHandler.class));
        assertNotNull(node);
        assertEquals(node.getInputKeys().size(), 1);
    }

    @Test
    public void ExitNode_NumOutputsConstructor(){
        ExitNode<String> node = new ExitNode<String>(2, 
                    mock(IResourceResolver.class), mock(IGraphOutputHandler.class));
        assertNotNull(node);
        assertEquals(node.getInputKeys().size(), 2);
    }

    @Test 
    public void ExitNode_InputReady_GoodInputs(){
        MockExitNode mockNode = getMockNode();
        mockNode.node.InputReady("Input1", "i1");
        mockNode.node.InputReady("Input2", "i2");
        mockNode.node.InputReady("Input3", "i3");
        
        verify(mockNode.resolver, times(1)).getResource("i1");
        verify(mockNode.resolver, times(1)).getResource("i2");
        verify(mockNode.resolver, times(1)).getResource("i3");
        verify(mockNode.handler, times(1)).handleOutput("Input1", "i1");
        verify(mockNode.handler, times(1)).handleOutput("Input2", "i2");
        verify(mockNode.handler, times(1)).handleOutput("Input3", "i3");
    }

    @Test
    public void ExitNode_InputReady_BadInputs(){
        MockExitNode mockNode = getMockNode();
        Exception ex = null;
        try{
            mockNode.node.InputReady("Bad_input", "i1");
        } catch(IllegalArgumentException e){
            ex = e;
        }
        assertNotNull(ex);
    }

    @Test
    public void ExitNode_InputReady_BadLookupKey(){
        MockExitNode mockNode = getMockNode();
        Exception ex = null;
        try{
            mockNode.node.InputReady("Input1", "bad_lookup");
        } catch(IllegalArgumentException e){
            ex = e;
        }
        assertNotNull(ex);
    }

}

