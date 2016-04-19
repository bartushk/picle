package net.bartushk.picle.Filter;

import java.util.Arrays;
import java.util.List;

import org.opencv.core.Mat;

public class ColorFilter implements IFilter{
    private FilterProperty redFilter; 
    private FilterProperty greenFilter; 
    private FilterProperty blueFilter; 


    public Mat applyFilter(Mat inputImage){
        return inputImage.clone();
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
}

