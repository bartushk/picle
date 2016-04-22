package net.bartushk.picle;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class ImageUtils
{
    public static double SharpnessScore(Mat inputImage){
        Mat xKernel = new Mat(1, 3, CvType.CV_32F){{
            put(0,0,-1); 
            put(0,1, 0); 
            put(0,2, 1); 
        }};

        Mat yKernel = new Mat(3, 1, CvType.CV_32F){{
            put(0,0,-1); 
            put(1,0, 0); 
            put(2,0, 1); 
        }};

        Mat gradX = new Mat();
        Mat gradY = new Mat();

        Imgproc.filter2D(inputImage, gradX, -1, xKernel);
        Imgproc.filter2D(inputImage, gradY, -1, yKernel);

        Core.multiply(gradX, gradX, gradX);
        Core.multiply(gradY, gradY, gradY);

        Mat gradSum = new Mat();
        Core.add(gradX, gradY, gradSum);
        Scalar elemSum = Core.sumElems(gradSum);
        double sum = 0;
        for( int i = 0; i < elemSum.val.length; i++){
            sum += elemSum.val[i];
        }
        sum /= elemSum.val.length;
        return sum;
    }
}
