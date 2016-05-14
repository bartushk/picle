package net.bartushk.picle.Filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.opencv.core.Mat;

import net.bartushk.picle.Core.IOperation;

public class FilterOperation implements IOperation<Mat>
{
    private static final String OUTPUT_KEY = "OutputImage";
    private static final String INPUT_KEY = "InputImage";
    private IFilter _filter;
    
    public FilterOperation(IFilter filter){
        this._filter = filter;
    }

    public List<String> getOutputKeys(){
        ArrayList<String> outputKeys = new ArrayList<String>();
        outputKeys.add(OUTPUT_KEY);
        return outputKeys;
    }

    public List<String> getInputKeys(){
        ArrayList<String> inputKeys = new ArrayList<String>();
        inputKeys.add(INPUT_KEY);
        return inputKeys;
    }

    public HashMap<String, Mat> process( HashMap<String, Mat> inputs ){
        HashMap<String, Mat> result = new HashMap<String, Mat>();
        Mat input = inputs.get(INPUT_KEY);
        result.put(OUTPUT_KEY, this._filter.applyFilter(input) );
        return result;
    }

}
