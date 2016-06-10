package net.bartushk.picle;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import net.bartushk.picle.Filter.*;

public class Program
{

    public static void imgShow(Mat img){
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", img, matOfByte);
        byte[] byteArray = matOfByte.toArray();
        BufferedImage bufImage = null;
        try{
            InputStream in = new ByteArrayInputStream(byteArray);
            bufImage = ImageIO.read(in);
            JFrame frame = new JFrame();
            frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
            frame.pack();
            frame.setVisible(true);
        } catch( Exception e ){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        String path = Program.class.getResource("/images/cat1.jpeg").getPath();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat img = Imgcodecs.imread(path);
        Filter filter = new OutlineFilter();
        filter.setProperty("Thickness", 3.0);
        filter.setProperty("Strength", 3.0);
        Mat filteredImg = filter.applyFilter(img);
        filter.setProperty("Thickness", 3.0);
        filter.setProperty("Strength", 1.0);
        Mat filteredImg2 = filter.applyFilter(img);

        imgShow(img);
        imgShow(filteredImg);
        imgShow(filteredImg2);
    }
}

