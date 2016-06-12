package net.bartushk.picletest.Graph;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import net.bartushk.picle.Core.IResourceResolver;
import net.bartushk.picle.Graph.ExitNode;
import net.bartushk.picle.Graph.IGraphOutputHandler;


public class ExitNodeTest
{

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

}

