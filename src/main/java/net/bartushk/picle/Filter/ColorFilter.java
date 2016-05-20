package net.bartushk.picle.Filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

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

    /**
     *
     * Default constructor that intializes 3 properties to control the
     * scaling of the color channels for this filter.
     *
     */
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

    /**
     *
     * Applies a scale to the each color channel based on the 'Red', 'Green', and
     * 'Blue' properties of the filter. Returns a copy of the image if it is not
     * a color image (if it has less than 3 channels.)
     *
     * @param inputImage the image that will have its color values scaled.
     */
    public Mat applyFilter(Mat inputImage){
        Mat returnImage = new Mat();
        List<Mat> channels = new ArrayList<Mat>();
        Core.split(inputImage, channels);
        int channelSize = channels.size();

        if( channelSize == 3 || channelSize == 4 ){
            double redValue = properties.get("Red").getValue();
            double greenValue = properties.get("Green").getValue();
            double blueValue = properties.get("Blue").getValue();
            Core.multiply(channels.get(0), new Scalar(blueValue), channels.get(0));
            Core.multiply(channels.get(1), new Scalar(greenValue), channels.get(1));
            Core.multiply(channels.get(2), new Scalar(redValue), channels.get(2));
            Core.merge(channels, returnImage);
        } else {
            returnImage = inputImage.clone();
        }

        return returnImage;
    }
}

