package net.bartushk.picletest.Filter;

import net.bartushk.picle.Filter.*;
import net.bartushk.picle.*;
import static net.bartushk.picletest.TestUtils.EPSILON;

import static org.junit.Assert.*;
import org.junit.Test;
import org.opencv.core.Mat;

public class OutlineFilterTest extends BaseFilterTest
{
    public OutlineFilterTest(){
        super();
    }


    @Test
    public void OutlineFilterTest_WhenConstructed_ThicknessPropertyIntialized(){
        OutlineFilter filter = new OutlineFilter();
        FilterProperty prop = filter.getProperty("Thickness"); 
        assertEquals(prop.getName(), "Thickness");
        assertEquals(prop.getValue(), 5.0, EPSILON);
        assertEquals(prop.getMin(), 3.0, EPSILON);
        assertEquals(prop.getMax(), 7.0, EPSILON);
    }

    @Test
    public void OutlineFilterTest_WhenConstructed_StrengthPropertyIntialized(){
        OutlineFilter filter = new OutlineFilter();
        FilterProperty prop = filter.getProperty("Strength"); 
        assertEquals(prop.getName(), "Strength");
        assertEquals(prop.getValue(), 1.0, EPSILON);
        assertEquals(prop.getMin(), 0.1, EPSILON);
        assertEquals(prop.getMax(), 3.0, EPSILON);
    }

    @Test
    public void OutlineFilterTest_WhenApplied_ImageIsSharper(){
        OutlineFilter filter = new OutlineFilter();
        filter.setProperty("Sharpness", 0.5);
        
        Mat result = filter.applyFilter(catImage);

        assertTrue( ImageUtils.SharpnessScore(catImage) < ImageUtils.SharpnessScore(result));
    }

}
