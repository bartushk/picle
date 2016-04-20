package net.bartushk.picle.Filter;

import java.util.Arrays;
import java.util.List;

import org.opencv.core.Mat;

/**
 *
 * A filter that will adjust the intensity of the red green and blue values of a color image.
 * This filter has three properties: "Red", "Green", and "Blue" which can have values between
 * 0 and 1 that are used to scale the color saturation in the image.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class ColorFilter extends Filter{

    public ColorFilter(){
        super();
        FilterProperty redProperty = new FilterProperty("Red");
        FilterProperty greenProperty = new FilterProperty("Green");
        FilterProperty blueProperty = new FilterProperty("Blue");
        
        redProperty.setStep(0.01);
        blueProperty.setStep(0.01);
        greenProperty.setStep(0.01);

        properties.put("Red", redProperty);
        properties.put("Green", greenProperty);
        properties.put("Blue", blueProperty);
    }

    public Mat applyFilter(Mat inputImage){
        return inputImage.clone();
    }
}

