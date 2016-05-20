package net.bartushk.picle.Filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.opencv.core.Mat;

import net.bartushk.picle.Core.IOperation;

/**
 *
 * An implementation of IOperation that passes the arguments to process
 * through an IFilter. This class will always have one input and one output,
 * and they will be keyed by OUTPUT_KEY and INPUT_KEY.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class FilterOperation implements IOperation<Mat>
{
    // Key for the mat returned by process.
    private static final String OUTPUT_KEY = "OutputImage";

    // Key for the mat passed into process.
    private static final String INPUT_KEY = "InputImage";

    // Filteer used by process.
    private IFilter _filter;
    
    /**
     *
     * A filter is injected to be used in the process
     * function.
     *
     * @param filter filter used by process.
     */
    public FilterOperation(IFilter filter){
        this._filter = filter;
    }

    
    /**
     *
     * Returns the keys for the outputs returned by process.
     * A filter operatoin will always have one image output,
     * placed at the key OUTPUT_KEY.
     *
     * @author Kyle Bartush
     * @since 0.1
     */
    public List<String> getOutputKeys(){
        ArrayList<String> outputKeys = new ArrayList<String>();
        outputKeys.add(OUTPUT_KEY);
        return outputKeys;
    }


    /**
     *
     * Returns the keys for the inputs required by process.
     * A filter operatoin will always have one image input,
     * placed at the key INPUT_KEY.
     *
     * @author Kyle Bartush
     * @since 0.1
     */
    public List<String> getInputKeys(){
        ArrayList<String> inputKeys = new ArrayList<String>();
        inputKeys.add(INPUT_KEY);
        return inputKeys;
    }


    /**
     *
     * Feeds the input passed in at INPUT_KEY through a filter
     * and returns the result at the key OUTPUT_KEY.
     *
     * @author Kyle Bartush
     * @since 0.1
     */
    public HashMap<String, Mat> process( HashMap<String, Mat> inputs ){
        if(!inputs.containsKey(INPUT_KEY))
            throw new IllegalArgumentException("The input to process must contain: " + INPUT_KEY);
        HashMap<String, Mat> result = new HashMap<String, Mat>();
        Mat input = inputs.get(INPUT_KEY);
        result.put(OUTPUT_KEY, this._filter.applyFilter(input) );
        return result;
    }

}
