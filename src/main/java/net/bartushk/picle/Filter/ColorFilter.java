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
public class ColorFilter implements IFilter{
    private FilterProperty redFilter; 
    private FilterProperty greenFilter; 
    private FilterProperty blueFilter; 


    public ColorFilter(){
        redFilter = new FilterProperty("Red");
        greenFilter = new FilterProperty("Green");
        blueFilter = new FilterProperty("Blue");

        redFilter.setStep(0.01);
        blueFilter.setStep(0.01);
        greenFilter.setStep(0.01);
    }

    public FilterProperty getProperty(String name){
        if(name.equals("Red")){
            return new FilterProperty(redFilter);
        }else if(name.equals("Blue")){
            return new FilterProperty(blueFilter);
        }else if(name.equalsIgnoreCase("Green")){
            return new FilterProperty(greenFilter);
        }
        return null;
    }

    public void setProperty(String name, double value){
        if(name.equals("Red")){
            redFilter.setValue(value);
        }else if(name.equals("Blue")){
            blueFilter.setValue(value);
        }else if(name.equalsIgnoreCase("Green")){
            greenFilter.setValue(value);
        }
    }

    public List<String> getPropertyNames(){
        return Arrays.asList("Red", "Blue", "Green");
    }

    public Mat applyFilter(Mat inputImage){
        return inputImage.clone();
    }
}

