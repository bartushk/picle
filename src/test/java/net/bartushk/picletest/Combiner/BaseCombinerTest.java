package net.bartushk.picletest.Combiner;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import net.bartushk.picle.Program;

public class BaseCombinerTest
{
    public Mat catImageOne;
    public Mat catImageScribble;
    public Mat catImageCreepy;

    public BaseCombinerTest(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        String path1 = Program.class.getResource("/images/cat1.jpeg").getPath();
        String path2 = Program.class.getResource("/images/cat1scribble.jpeg").getPath();
        String path3 = Program.class.getResource("/images/cat1creepy.jpeg").getPath();
        catImageOne = Imgcodecs.imread(path1);
        catImageScribble = Imgcodecs.imread(path2);
        catImageCreepy = Imgcodecs.imread(path3);
    }
}

