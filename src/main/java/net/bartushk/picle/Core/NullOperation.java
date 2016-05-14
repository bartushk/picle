package net.bartushk.picle.Core;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * Basic implementation of IOperation that takes no inputs or outputs
 * and does nothing. Not sure why it exists.
 *
 * @author Kyle Bartush
 * @see 
 * @since 0.1
 */
public class NullOperation<T> implements IOperation<T>
{
    public List<String> getInputKeys(){
        return new ArrayList<String>();
    }

    public List<String> getOutputKeys(){
        return new ArrayList<String>();
    }

    public HashMap<String, T> process( HashMap<String, T> inputs ){
        return new HashMap<String, T>();
    }
}
