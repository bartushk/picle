package net.bartushk.picletest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;


public class OpencvTest
{
    @Test
    public void OpencvBasicTest(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        byte buff[] = new byte[1];
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(4));
        m.get(0, 0, buff);
        assertEquals( buff[0], 4);
    }
}
