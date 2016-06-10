package net.bartushk.picletest.Filter;

import net.bartushk.picle.Filter.*;
import net.bartushk.picle.*;
import net.bartushk.picle.Core.DiscreteStepProperty;

import static net.bartushk.picletest.TestUtils.EPSILON;

import static org.junit.Assert.*;
import org.junit.Test;
import org.opencv.core.Mat;


public class SharpnessFilterTest extends BaseFilterTest
{
    public SharpnessFilterTest(){
        super();
    }


    @Test
    public void SharpnessFilter_WhenConstructed_SharpnessPropertyIntialized(){
        SharpnessFilter filter = new SharpnessFilter();
        DiscreteStepProperty prop = filter.getProperty("Sharpness"); 
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
