package net.bartushk.picletest.Graph;


import net.bartushk.picle.Core.IOperation;
import net.bartushk.picle.Core.IResourceResolver;
import net.bartushk.picle.Graph.Edge;
import net.bartushk.picle.Graph.ProcessingNode;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;


public class ProcessingNodeTest
{
    private class mockNode<T>
    {
        public IOperation<T> operation;
        public IResourceResolver<T> resolver;
        public ExecutorService executor;
        public ProcessingNode<T> node;
    }

    public <T> mockNode<T> getMockNode(String nodeKey){
        mockNode<T> returnNode = new mockNode<T>();
        returnNode.operation = (IOperation<T>)mock(IOperation.class);
        returnNode.resolver = (IResourceResolver<T>)mock(IResourceResolver.class);
        returnNode.executor = (ExecutorService)mock(ExecutorService.class);
        List<String> inputKeys = new ArrayList<String>();
        inputKeys.add("InOne");
        inputKeys.add("InTwo");
        inputKeys.add("InThree");

        List<String> outputKeys = new ArrayList<String>();
        outputKeys.add("OutOne");
        outputKeys.add("OutTwo");

        when(returnNode.operation.getInputKeys()).thenReturn(inputKeys);
        when(returnNode.operation.getOutputKeys()).thenReturn(outputKeys);

        ProcessingNode<T> pNode = new  ProcessingNode<T>(nodeKey, 
                                            returnNode.operation,
                                            returnNode.resolver,
                                            returnNode.executor);
        returnNode.node = pNode;
        return returnNode;
    }

    @Test 
    public void ProcessingNode_Constructor(){
        mockNode<String> testNode = getMockNode("Test");
        assertEquals(testNode.node.getNodeKey(), "Test");
    }

    @Test
    public void ProcessingNode_KeysMatchOperation(){
        mockNode<String> testNode = getMockNode("Test");

        assertEquals(testNode.node.getInputKeys(), testNode.operation.getInputKeys());
        assertEquals(testNode.node.getOutputKeys(), testNode.operation.getOutputKeys());
    }

    @Test
    public void ProcessingNode_TriggeredCorrectlyAfterAllInputs(){
        mockNode<String> testNode = getMockNode("Test");

        testNode.node.InputReady("InOne", "LookupOne");
        testNode.node.InputReady("InTwo", "LookupTwo");
        testNode.node.InputReady("InThree", "LookupThree");

        verify(testNode.executor, times(1)).execute(testNode.node);
    }

    @Test
    public void ProcessingNode_NotTriggeredAfterRepeatInputs(){
        mockNode<String> testNode = getMockNode("Test");

        testNode.node.InputReady("InOne", "LookupOne");
        testNode.node.InputReady("InTwo", "LookupTwo");
        testNode.node.InputReady("InTwo", "LookupThree");

        verify(testNode.executor, times(0)).execute(testNode.node);
    }


    @Test
    public void ProcessingNode_Run(){
        String nodeKey = "Test";
        mockNode<String> testNode = getMockNode(nodeKey);
        HashMap<String, String> opReturn = new HashMap<String, String>();
        opReturn.put("OutOne", "ResOne");
        opReturn.put("OutTwo", "ResTwo");

        Edge mockEdgeOne = mock(Edge.class);
        Edge mockEdgeTwo = mock(Edge.class);
        ProcessingNode mockNodeOne = mock(ProcessingNode.class);
        ProcessingNode mockNodeTwo = mock(ProcessingNode.class);

        when(mockEdgeOne.getFromKey()).thenReturn("OutOne");
        when(mockEdgeTwo.getFromKey()).thenReturn("OutTwo");
        when(mockEdgeOne.getToKey()).thenReturn("ToKeyOne");
        when(mockEdgeTwo.getToKey()).thenReturn("ToKeyTwo");
        when(mockEdgeOne.getToNode()).thenReturn(mockNodeOne);
        when(mockEdgeTwo.getToNode()).thenReturn(mockNodeTwo);
        when(testNode.resolver.getResource(any(String.class))).thenReturn("TestRes");
        when(testNode.operation.process(any(opReturn.getClass()))).thenReturn(opReturn);

        testNode.node.addFromEdge(mockEdgeOne);
        testNode.node.addFromEdge(mockEdgeTwo);
        testNode.node.InputReady("InOne", "LookupOne");
        testNode.node.InputReady("InTwo", "LookupTwo");
        testNode.node.InputReady("InThree", "LookupThree");

        testNode.node.run();

        verify(testNode.resolver, times(1)).getResource("LookupOne"); 
        verify(testNode.resolver, times(1)).getResource("LookupTwo"); 
        verify(testNode.resolver, times(1)).getResource("LookupThree"); 
        verify(testNode.resolver, times(1)).putResource(nodeKey + "OutOne", "ResOne");
        verify(testNode.resolver, times(1)).putResource(nodeKey + "OutTwo", "ResTwo");
        verify(mockNodeOne, times(1)).InputReady("ToKeyOne", nodeKey + "OutOne" );
        verify(mockNodeTwo, times(1)).InputReady("ToKeyTwo", nodeKey + "OutTwo" );

    }

}
