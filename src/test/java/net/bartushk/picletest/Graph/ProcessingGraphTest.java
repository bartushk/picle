package net.bartushk.picletest.Graph;

import net.bartushk.picle.Core.IResourceResolver;
import net.bartushk.picle.Graph.EntryNode;
import net.bartushk.picle.Graph.ExitNode;
import net.bartushk.picle.Graph.IGraphOutputHandler;
import net.bartushk.picle.Graph.IProcessingGraphFactory;
import net.bartushk.picle.Graph.Node;
import net.bartushk.picle.Graph.ProcessingGraph;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class ProcessingGraphTest
{
    private class mockGraph
    {
        public ProcessingGraph<String> graph;
        public IResourceResolver<String> resolver;
        public IGraphOutputHandler<String> handler;
        public EntryNode<String> entryNode;
        public ExitNode<String> exitNode;
    }

    private mockGraph getMockGraph(){
        mockGraph returnGraph = new mockGraph();
        List<String> graphInputs = new ArrayList<String>();
        graphInputs.add("First_Input");
        graphInputs.add("Second_Input");
        graphInputs.add("Third_Input");
        EntryNode<String> entryNode = (EntryNode<String>)mock(EntryNode.class);
        when(entryNode.getOutputKeys()).thenReturn(graphInputs);
        ExitNode<String> exitNode = (ExitNode<String>)mock(ExitNode.class);
        IProcessingGraphFactory<String> factory = (IProcessingGraphFactory<String>)mock(IProcessingGraphFactory.class);
        IResourceResolver<String> res = (IResourceResolver<String>)mock(IResourceResolver.class);
        IGraphOutputHandler<String> out = (IGraphOutputHandler<String>)mock(IGraphOutputHandler.class);
        when(factory.getResourceResolver()).thenReturn(res);
        when(factory.getOutputHandler()).thenReturn(out);
        when(factory.getEntryNode()).thenReturn(entryNode);
        when(factory.getExitNode()).thenReturn(exitNode);

        ProcessingGraph<String> graph = new ProcessingGraph<String>(factory);
        returnGraph.graph = graph;
        returnGraph.resolver = res;
        returnGraph.handler = out;
        returnGraph.exitNode = exitNode;
        returnGraph.entryNode = entryNode;
        return returnGraph;
    }


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

    @Test
    public void ProcessingGraph_setInputCount(){
        ProcessingGraph graph = new ProcessingGraph();
        graph.setInputCount(2);
        assertEquals(graph.getInputCount(), 2);
    }

    @Test
    public void ProcessingGraph_setOutputCount(){
        ProcessingGraph graph = new ProcessingGraph();
        graph.setOutputCount(2);
        assertEquals(graph.getOutputCount(), 2);
    }

    @Test
    public void ProcessingGraph_startProcessing_ValidInput(){
        mockGraph mock = getMockGraph();
        HashMap<String, String> inputs = new HashMap<String,String>();
        inputs.put("First_Input", "this");
        inputs.put("Second_Input", "that");
        inputs.put("Third_Input", "dude");
        Exception ex = null;
        try{
            mock.graph.startProcessing(inputs);
        } catch(Exception e){
            ex = e;
        }

        assertNull(ex);
        verify(mock.entryNode, times(1)).graphInputReady("First_Input", "this");
        verify(mock.entryNode, times(1)).graphInputReady("Second_Input", "that");
        verify(mock.entryNode, times(1)).graphInputReady("Third_Input", "dude");
    }

    @Test
    public void ProcessngGraph_startProcessing_InvalidInput(){
        mockGraph mock = getMockGraph();
        HashMap<String, String> inputs = new HashMap<String,String>();
        inputs.put("First_Input", "this");
        inputs.put("Second_Input", "that");

        // Test for not all inputs.
        Exception ex = null;
        try{
            mock.graph.startProcessing(inputs);
        } catch( Exception e){
            ex = e;
        }

        assertEquals(ex.getMessage(), "The provided inputs do not match the graph inputs.");

        // Tes for bad input name.
        inputs.put("NotAnInput", "aa");
        ex = null;
        try{
            mock.graph.startProcessing(inputs);
        } catch( Exception e){
            ex = e;
        }

        assertEquals(ex.getMessage(), "The provided inputs do not match the graph inputs.");
    }

}
