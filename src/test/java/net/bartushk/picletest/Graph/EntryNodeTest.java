package net.bartushk.picletest.Graph;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import net.bartushk.picle.Core.IResourceResolver;
import net.bartushk.picle.Graph.EntryNode;


public class EntryNodeTest
{

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

}
