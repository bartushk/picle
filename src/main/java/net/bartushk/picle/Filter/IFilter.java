package net.bartushk.picle.Filter;

import java.util.List;

import org.opencv.core.Mat;

/**
 *
 * The filter interface defines a basic set of operations that can be applied to 
 * an image in the form of an OpenCV Mat. Each filter should consist of one or more
 * operations applied to a mat to acheive a desired effect. It may also contain
 * properties that can be adjusted to control the behavior of the filter. 
 *
 * <p>An example of a filter may be a color filter, that adjusts the Red Green and Blue
 * values of an Image. This filter could also expose the Red, Green, and Blue properties
 * to allow its effect to be easily adjusted.
 *
 * @author Kyle Bartush
 * @since 0.1
 */
public interface IFilter{

    Mat applyFilter(Mat inputImage);

    FilterProperty getProperty(String name);

    void setProperty(String name, double value);

    List<String> getPropertyNames();
    
}
