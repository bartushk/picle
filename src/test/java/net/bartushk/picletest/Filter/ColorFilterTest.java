package net.bartushk.picletest.Filter;

import org.junit.Test;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

import net.bartushk.picle.Filter.*;
import static net.bartushk.picletest.TestUtils.EPSILON;
import static org.junit.Assert.*;


public class ColorFilterTest extends BaseFilterTest
{
    public ColorFilterTest(){
        super();
    }

    @Test
    public void ColorFilter_WhenConstructed_RedPropertyIntialized(){
        ColorFilter filter = new ColorFilter();
        FilterProperty prop = filter.getProperty("Red"); 
        assertEquals(prop.getName(), "Red");
        assertEquals(prop.getValue(), 1.0, EPSILON);
        assertEquals(prop.getMin(), 0.0, EPSILON);
        assertEquals(prop.getMax(), 1.0, EPSILON);
        assertEquals(prop.getStep(), 0.01, EPSILON);
    }

    @Test
    public void ColorFilter_WhenConstructed_BluePropertyIntialized(){
        ColorFilter filter = new ColorFilter();
        FilterProperty prop = filter.getProperty("Blue"); 
        assertEquals(prop.getName(), "Blue");
        assertEquals(prop.getValue(), 1.0, EPSILON);
        assertEquals(prop.getMin(), 0.0, EPSILON);
        assertEquals(prop.getMax(), 1.0, EPSILON);
        assertEquals(prop.getStep(), 0.01, EPSILON);
    }

    @Test
    public void ColorFilter_WhenConstructed_GreenPropertyIntialized(){
        ColorFilter filter = new ColorFilter();
        FilterProperty prop = filter.getProperty("Green"); 
        assertEquals(prop.getName(), "Green");
        assertEquals(prop.getValue(), 1.0, EPSILON);
        assertEquals(prop.getMin(), 0.0, EPSILON);
        assertEquals(prop.getMax(), 1.0, EPSILON);
        assertEquals(prop.getStep(), 0.01, EPSILON);
    }

    @Test
    public void ColorFilter_ApplyFilter_RedPropertyWorking(){
        ColorFilter filter = new ColorFilter();
        filter.setProperty("Red", 0);

        Mat transformed = filter.applyFilter(catImage);
        Scalar elements = Core.sumElems(transformed);

        double blueSum = elements.val[0];
        double greenSum = elements.val[1];
        double redSum = elements.val[2];

        assertNotEquals(blueSum, 0, EPSILON);
        assertNotEquals(greenSum, 0, EPSILON);
        assertEquals(redSum, 0, EPSILON);
    }

    @Test
    public void ColorFilter_ApplyFilter_BluePropertyWorking(){
        ColorFilter filter = new ColorFilter();
        filter.setProperty("Blue", 0);

        Mat transformed = filter.applyFilter(catImage);
        Scalar elements = Core.sumElems(transformed);

        double blueSum = elements.val[0];
        double greenSum = elements.val[1];
        double redSum = elements.val[2];

        assertEquals(blueSum, 0, EPSILON);
        assertNotEquals(greenSum, 0, EPSILON);
        assertNotEquals(redSum, 0, EPSILON);
    }

    @Test
    public void ColorFilter_ApplyFilter_GreenPropertyWorking(){
        ColorFilter filter = new ColorFilter();
        filter.setProperty("Green", 0);

        Mat transformed = filter.applyFilter(catImage);
        Scalar elements = Core.sumElems(transformed);

        double blueSum = elements.val[0];
        double greenSum = elements.val[1];
        double redSum = elements.val[2];

        assertNotEquals(blueSum, 0, EPSILON);
        assertEquals(greenSum, 0, EPSILON);
        assertNotEquals(redSum, 0, EPSILON);
    }

}
