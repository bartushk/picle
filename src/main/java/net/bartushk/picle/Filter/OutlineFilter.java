package net.bartushk.picle.Filter;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;



/**
 *
 * A filter that gives the ability to create an 'outline' of an image. The user will
 * be able to control the 'thickness' of the outline created from the filter, as well as
 * a 'strength' property which will effect how bright the exposed outlines are.
 *
 * <p> This effect will be accomplished by using a sobel filter to create a gradient image.
 * The 'thickness' property will will determine the size of ther kernel, and the 'strength'
 * property will skill the gradient image result.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class OutlineFilter extends Filter{
    
    /*
     *
     * Adds a 'thickness' and 'Strength' property to be used by the filter.
     */
    public OutlineFilter(){
        super();
        FilterProperty thicknessProperty = new FilterProperty("Thickness");
        FilterProperty strengthProperty = new FilterProperty("Strength");

        thicknessProperty.setStep(2);
        thicknessProperty.setMin(3);
        thicknessProperty.setMax(7);
        thicknessProperty.setValue(5);

        strengthProperty.setStep(0.1);
        strengthProperty.setMin(0.1);
        strengthProperty.setMax(3);
        strengthProperty.setValue(1);

        properties.put("Thickness", thicknessProperty);
        properties.put("Strength", strengthProperty);
    }


    /**
     * Function that actually applies the image transformation.
     * 
     * <p> This effect will be accomplished by using a sobel filter to create a gradient image.
     * The 'thickness' property will will determine the size of ther kernel, and the 'strength'
     * property will skill the gradient image result.
     *
     * @param inputImage image to be transformed into an outline image.
     */
    public Mat applyFilter(Mat inputImage){
        double thicknessValue = properties.get("Thickness").getValue(); 
        double scaleValue = properties.get("Strength").getValue(); 
        if( thicknessValue > -0.005 && thicknessValue < 0.005 ){
            return inputImage.clone(); 
        }

        Mat returnImage = new Mat();
        Imgproc.Sobel(inputImage, returnImage, -1, 1, 1, 
                        (int)thicknessValue, scaleValue, 5);

        return returnImage;
    }

}
