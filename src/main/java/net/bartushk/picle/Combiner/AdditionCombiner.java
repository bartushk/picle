package net.bartushk.picle.Combiner;

import java.util.List;

import org.opencv.core.Mat;

import net.bartushk.picle.Core.DiscreteStepProperty;

/**
 *
 * A combiner that takes a list of images and sums them all. This combiner only has
 * one property (InputCount), the number of inputs it accepts.
 *
 * @author Kyle Bartush
 * @see 
 * @since 0.1
 */
public class AdditionCombiner extends Combiner
{
    /**
     *
     * Default constructor, initialized the InputCount property to two.
     *
     */
    public AdditionCombiner(){
        super();
        DiscreteStepProperty countProperty = new DiscreteStepProperty("InputCount");
        countProperty.setMin(2.0);
        countProperty.setMax(6.0);
        countProperty.setStep(1.0);
        countProperty.setValue(2.0);
        properties.put("InputCount", countProperty);
    }

    public Mat applyCombiner(List<Mat> inputImages){
        return null;
    }

    public List<String> getInputNames(){
        return null;
    }

}
