package net.bartushk.picletest.Graph;

import net.bartushk.picle.Core.IResourceResolver;
import net.bartushk.picle.Graph.IGraphOutputHandler;
import net.bartushk.picle.Graph.ProcessingGraph;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ProcessingGraphTest
{
    @Test
    public void ProcessingGraph_DefaultConstructor(){
        ProcessingGraph graph = new ProcessingGraph(); 
        assertNotNull(graph);
    }

    @Test
    public void ProcessingGraph_ConstructorTwo(){
        ProcessingGraph graph = new ProcessingGraph(
            mock(IResourceResolver.class), mock(IGraphOutputHandler.class));
        assertNotNull(graph);
    }

}
