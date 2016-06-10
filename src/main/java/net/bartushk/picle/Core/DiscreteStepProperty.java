package net.bartushk.picle.Core;


/**
 *
 * Basic struct like class used for holding information about one of an editable
 * properties. For example, a color filter may have 3 properties named: Red Green Blue,
 * which could be used to dictate the amount each fundamental color type
 * is adjusted by the filter.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class DiscreteStepProperty{
    
    /*
     *
     * Every property has a name to identify it, which should be unique. Each property
     * also has a range of values it can take on; defined by max, min, and step.
     *
     */
    private String name;
    private double step;
    private double max;
    private double min;
    private double value;


    public DiscreteStepProperty(DiscreteStepProperty toCopy){
        this(   toCopy.getName(), 
                toCopy.getMax(),
                toCopy.getMin(),
                toCopy.getStep(),
                toCopy.getValue());
    }

    public DiscreteStepProperty(String name, double max, double min, double step, double value){
        this.name = name;
        this.max = max;
        this.min = min;
        this.step = step;
        this.value = value;
    }

    public DiscreteStepProperty(String name){
        this(name, 1.0, 0.0, 0.1, 1.0);
    }

    public DiscreteStepProperty(){
        this("No-Name");
    }
    
    /*
     * If a value is passed outside of the min and max, it is not set.
     */

    public void setValue(double value){
        if(value >= this.min && value <= this.max){
            this.value = value;
        }
    }

    //name getter and setter
    public String getName(){
        return name;
    }
   
    public void setName(String name){
        this.name = name;
    }

    //max getter and setter
    public double getMax(){
        return max;
    }
   
    public void setMax(double max){
        this.max = max;
    }

    //min getter and setter
    public double getMin(){
        return min;
    }
   
    public void setMin(double min){
        this.min = min;
    }

    //step getter and setter
    public double getStep(){
        return step;
    }
    
    public void setStep(double step){
        this.step = step;
    }

    //value getter and setter
    public double getValue(){
        return value;
    }
    
}
