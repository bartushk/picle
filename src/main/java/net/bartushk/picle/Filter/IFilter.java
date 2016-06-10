package net.bartushk.picle.Filter;

import java.util.List;

import org.opencv.core.Mat;

/**
 *
 * The filter interface defines a basic set of methods that can be applied to 
 * an image in the form of an OpenCV Mat. Each filter should consist of one or more
 * operations applied to a mat to acheive a desired effect. It is a single input
 * single output operation.
 *
 * <p>An example of a filter may be a color filter. Such a filter could adjust
 * the Red, Green, and Blue values of an Image. 
 *
 * @author Kyle Bartush
 * @since 0.1
 */

public interface IFilter
{
    Mat applyFilter(Mat inputImage);
}
