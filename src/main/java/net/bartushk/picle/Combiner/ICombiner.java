package net.bartushk.picle.Combiner;

import java.util.List;

import org.opencv.core.Mat;

/**
 *
 * The combiner interface defines a basic set of methods that can be applied to
 * an image in the form of an OpenCV Mat. Each combiner should consist of one or more
 * operations applied to a mat to acheive the desired effect. It is a multiple input
 * single output operation.
 *
 * <p>An example of a combiner may take two images as inputs and do a pixel-wise addition,
 * producing a single output.
 *
 * @author Kyle Bartush
 * @see 
 * @since 0.1
 */
public interface ICombiner
{
    Mat applyCombiner(List<Mat> inputImages);

    List<String> getInputNames();
}
