
package net.bartushk.picletest.Graph;


import net.bartushk.picle.Core.IOperation;
import net.bartushk.picle.Core.IResourceResolver;
import net.bartushk.picle.Graph.Node;
import net.bartushk.picle.Graph.ProcessingNode;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.concurrent.AbstractExecutorService;


public class ProcessingNodeTest
{
    private class mockNode<T>
    {
        public IOperation<T> operation;
        public IResourceResolver<T> reslover;
        public AbstractExecutorService executor;
        public ProcessingNode<T> node;
    }

    public <T> mockNode<T> getMockNode(String nodeKey){
        mockNode<T> returnNode = new mockNode<T>();
        returnNode.operation = (IOperation<T>)mock(IOperation.class);
        returnNode.reslover = (IResourceResolver<T>)mock(IResourceResolver.class);
        returnNode.executor = (AbstractExecutorService)mock(AbstractExecutorService.class);
        ProcessingNode<T> pNode = new  ProcessingNode<T>(nodeKey, 
                                            returnNode.operation,
                                            returnNode.reslover,
                                            returnNode.executor);
        returnNode.node = pNode;
        return returnNode;
    }

    @Test 
    public void ProcessingNode_Constructor(){
        mockNode<String> mockNode = getMockNode("Test");
        assertEquals(mockNode.node.getNodeKey(), "Test");
    }

}
