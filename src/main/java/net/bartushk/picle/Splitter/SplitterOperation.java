package net.bartushk.picle.Splitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.opencv.core.Mat;

import net.bartushk.picle.Core.IOperation;

/**
 *
 * An implementation of IOperation that passes the arguments to process
 * through an ISplitter. 
 *
 * @author Kyle Bartush
 * @see 
 * @since 0.1
 */
public class SplitterOperation implements IOperation<Mat>
{

    public List<String> getInputKeys(){
        return new ArrayList<String>();
    }

    public List<String> getOutputKeys(){
        return new ArrayList<String>();
    }

    public HashMap<String, Mat> process(HashMap<String, Mat> inputs){
        return new HashMap<String, Mat>();        
    }
}
