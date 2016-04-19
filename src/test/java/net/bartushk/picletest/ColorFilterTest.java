package net.bartushk.picletest;

import net.bartushk.picle.Filter.*;
import static net.bartushk.picletest.TestUtils.EPSILON;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;


public class ColorFilterTest
{

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

}
