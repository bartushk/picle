package net.bartushk.picle.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.bartushk.picle.Core.DiscreteStepProperty;

/**
 *
 * A PropertiedObject (name pending :/) is an object that has a variable amount
 * of discretely stepped properties. The main use case for this is a Filter/Split/Combine
 * operation that has adjustable parameters.
 *
 * <p>An example is a color filter, that allows certain percentages of each RGB value
 * through into the output image. A filter of this type would have 3 properties (RedPercent,
 * BluePercent, GreenPercent) that could be adjusted from 0-100 to indicate the percentage
 * of the color to filter out.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public abstract class PropertiedObject 
{
    
    protected HashMap<String, DiscreteStepProperty> properties;

    public PropertiedObject(){
        properties = new HashMap<String, DiscreteStepProperty>();
    }

    public DiscreteStepProperty getProperty(String name){
        if( properties.containsKey(name) ){
            return new DiscreteStepProperty(properties.get(name));
        }
        return null;
    }

    public List<String> getPropertyNames(){
        return new ArrayList<String>(properties.keySet());
    }

    public void setProperty(String name, double value){
        if( properties.containsKey(name) ){
            properties.get(name).setValue(value);
        }
    }
}
