package net.bartushk.picle.Splitter;

import java.util.List;

import org.opencv.core.Mat;

/**
 *
 * The splitter interface defines a basic set of methods that can be applied to
 * an image in the form of an OpenCV Mat. Each splitter should consist of one or more
 * operations applied to a mat to acheive the desired effect. It is a single input
 * multi output operation.
 *
 * <p>An example of something a splitter may do, is take a single input image
 * and split it into 3 seperate images, each representing the intensity of the 
 * input image's Red, Green, and Blue values.
 *
 * @author Kyle Bartush
 * @see 
 * @since 0.1
 */
public interface ISplitter
{
    List<Mat> applyCombiner(Mat inputImage);

    List<String> getOutputNames();
}
