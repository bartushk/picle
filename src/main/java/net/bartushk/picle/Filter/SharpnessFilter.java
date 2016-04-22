package net.bartushk.picle.Filter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 *
 * A filter that will adjust the sharpness of an image. This filter has a 'Sharpness'
 * property in the range [-1,1]. A 'Sharpness' of 0 will return an image clone. A negative
 * sharpness will return a blurred image and a positive value will return a sharpened image.
 *
 * <p> The sharpening effect is accomplished by applying a guasian blur to the original image
 * to blur it, and subtracting the blurred image from the original to sharpen.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public class SharpnessFilter extends Filter{

    // The max blur value, which is used in combination with the sharpness property
    // to determine the amount of blurring/sharpening.
    private static final double BLUR_FACTOR = 3;

    // The values used to determine the weighted average of the 
    // original image and the blurred image when executing the
    // sharpening operation. These should sum to 1. The closer
    // the ratio of ALPHA to BETA, the more of a sharpening effect.
    private static final double ALPHA = 2.0;
    private static final double BETA = -1.0;


    /**
     *
     * Adds a sharpness property to be used by the filter.
     */
    public SharpnessFilter(){
        super();
        FilterProperty sharpnessProperty = new FilterProperty("Sharpness");
        
        sharpnessProperty.setStep(0.01);
        sharpnessProperty.setValue(0);
        sharpnessProperty.setMax(1.0);
        sharpnessProperty.setMin(-1.0);
        
        properties.put("Sharpness", sharpnessProperty);
    }

    /**
     * Function that actually applies the image transformation.
     * 
     * <p> The sharpening effect is accomplished by applying a guasian blur to the original image
     * to blur it, and subtracting the blurred image from the original to sharpen.
     *
     * @param inputImage iamge to be sharpened/blurred.
     */
    public Mat applyFilter(Mat inputImage){
        double sharpnessValue = properties.get("Sharpness").getValue(); 
        if( sharpnessValue > -0.005 && sharpnessValue < 0.005 ){
            return inputImage.clone(); 
        }

        Mat returnImage = new Mat();
        boolean sharpen = sharpnessValue > 0;
        sharpnessValue = Math.abs(sharpnessValue);
        Imgproc.GaussianBlur(inputImage, returnImage, new Size(0,0), sharpnessValue * BLUR_FACTOR);

        // To sharpen, subtract the blurred image.
        if( sharpen ){
            Core.addWeighted(inputImage, ALPHA, returnImage, BETA, -1, returnImage);
        }

        return returnImage;
    }

}
