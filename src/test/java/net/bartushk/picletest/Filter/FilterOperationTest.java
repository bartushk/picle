package net.bartushk.picletest.Filter;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import net.bartushk.picle.Filter.FilterOperation;
import net.bartushk.picle.Filter.IFilter;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FilterOperationTest
{

    public FilterOperationTest(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @Test
    public void FilterOperation_Constructor(){
        FilterOperation testOp = new FilterOperation(mock(IFilter.class));
        assertNotNull(testOp);
    }

    @Test
    public void FilterOperation_Keys(){
        FilterOperation testOp = new FilterOperation(mock(IFilter.class));

        List<String> expectedInputs = new ArrayList<String>();
        expectedInputs.add("InputImage");

        List<String> expectedOutputs = new ArrayList<String>();
        expectedOutputs.add("OutputImage");

        assertEquals(testOp.getInputKeys(), expectedInputs);
        assertEquals(testOp.getOutputKeys(), expectedOutputs);

    }

    @Test(expected=IllegalArgumentException.class)
    public void FilterOperation_ProcessNoInputKey(){
        FilterOperation testOp = new FilterOperation(mock(IFilter.class));
        HashMap<String, Mat> inputs = new HashMap<String, Mat>();

        testOp.process(inputs);
    }

    @Test
    public void FilterOperation_ProcessArgsAndReturns(){
        IFilter mockFilter = mock(IFilter.class); 
        FilterOperation testOp = new FilterOperation(mockFilter);
        Mat inputMat = new Mat(3, 3, CvType.CV_8U);
        Mat outputMat = new Mat(5, 5, CvType.CV_32F);
        HashMap<String, Mat> inputs = new HashMap<String, Mat>();
        inputs.put("InputImage", inputMat);

        when(mockFilter.applyFilter(any(Mat.class))).thenReturn(outputMat);

        HashMap<String, Mat> result = testOp.process(inputs);
        
        assertEquals(result.get("OutputImage"), outputMat);
        verify(mockFilter, times(1)).applyFilter(inputMat);

    }
}
