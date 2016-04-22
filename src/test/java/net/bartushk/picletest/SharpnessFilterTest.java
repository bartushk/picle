package net.bartushk.picletest;

import net.bartushk.picle.Filter.*;
import net.bartushk.picle.*;
import static net.bartushk.picletest.TestUtils.EPSILON;

import static org.junit.Assert.*;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;


public class SharpnessFilterTest extends BaseFilterTest
{
    public SharpnessFilterTest(){
        super();
    }


    @Test
    public void SharpnessFilter_WhenConstructed_SharpnessPropertyIntialized(){
        SharpnessFilter filter = new SharpnessFilter();
        FilterProperty prop = filter.getProperty("Sharpness"); 
        assertEquals(prop.getName(), "Sharpness");
        assertEquals(prop.getValue(), 0.0, EPSILON);
        assertEquals(prop.getMin(), -1.0, EPSILON);
        assertEquals(prop.getMax(), 1.0, EPSILON);
        assertEquals(prop.getStep(), 0.01, EPSILON);
    }

    @Test
    public void SharpnessFilter_ApplyFilterWithNegativeSharpness_SharpnessDecreased(){
        SharpnessFilter filter = new SharpnessFilter();
        filter.setProperty("Sharpness", -0.5);
        
        Mat result = filter.applyFilter(catImage);

        assertTrue( ImageUtils.SharpnessScore(catImage) > ImageUtils.SharpnessScore(result));
    }

    @Test
    public void SharpnessFilter_ApplyFilterWithZeroSharpness_ImageEqual(){
        SharpnessFilter filter = new SharpnessFilter();
        filter.setProperty("Sharpness", 0);
        
        Mat result = filter.applyFilter(catImage);

        assertEquals( 
            ImageUtils.SharpnessScore(catImage),
            ImageUtils.SharpnessScore(result),
            EPSILON);
    }

    @Test
    public void SharpnessFilter_ApplyFilterWithNegativeSharpness_SharpnessIncreased(){
        SharpnessFilter filter = new SharpnessFilter();
        filter.setProperty("Sharpness", 0.5);
        
        Mat result = filter.applyFilter(catImage);

        assertTrue( ImageUtils.SharpnessScore(catImage) < ImageUtils.SharpnessScore(result));
    }
}
