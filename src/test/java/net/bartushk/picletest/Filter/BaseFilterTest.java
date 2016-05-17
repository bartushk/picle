package net.bartushk.picletest.Filter;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import net.bartushk.picle.Program;

public class BaseFilterTest
{
    public Mat catImage;

    public BaseFilterTest(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String path = Program.class.getResource("/images/cat1.jpeg").getPath();
        catImage = Imgcodecs.imread(path);
    }

}
