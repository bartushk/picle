package net.bartushk.picle.Filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * The Filter class is an abstract class that handles the property functoinality of the
 * IFilter interface by storing filter properties in a dictionary. If implementing this class
 * each hproperty should be put into the properties dictionary keyed by its name.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public abstract class Filter implements IFilter
{
    
    protected HashMap<String, FilterProperty> properties;

    public Filter(){
        properties = new HashMap<String, FilterProperty>();
    }

    public FilterProperty getProperty(String name){
        if( properties.containsKey(name) ){
            return new FilterProperty(properties.get(name));
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
