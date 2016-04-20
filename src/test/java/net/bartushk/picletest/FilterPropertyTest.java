package net.bartushk.picletest;

import java.util.List;

import org.junit.Test;

import org.opencv.core.Mat;

import net.bartushk.picle.Filter.*;
import static net.bartushk.picletest.TestUtils.EPSILON;
import static org.junit.Assert.*;


public class FilterPropertyTest
{

    @Test
    public void FilterProperty_DefaultConstructor(){
        FilterProperty prop = new FilterProperty();

        assertEquals(prop.getName(), "No-Name");
        assertEquals(prop.getMax(), 1.0, EPSILON);
        assertEquals(prop.getMin(), 0.0, EPSILON);
        assertEquals(prop.getValue(), 1.0, EPSILON);
        assertEquals(prop.getStep(), 0.1, EPSILON);
    }

    @Test
    public void FilterProperty_StringConstructor(){
        FilterProperty prop = new FilterProperty("TestName");

        assertEquals(prop.getName(), "TestName");
        assertEquals(prop.getMax(), 1.0, EPSILON);
        assertEquals(prop.getMin(), 0.0, EPSILON);
        assertEquals(prop.getValue(), 1.0, EPSILON);
        assertEquals(prop.getStep(), 0.1, EPSILON);
    }

    @Test
    public void FilterProperty_FullConstructor(){
        FilterProperty prop = new FilterProperty("TestName");

        assertEquals(prop.getName(), "TestName");
        assertEquals(prop.getMax(), 1.0, EPSILON);
        assertEquals(prop.getMin(), 0.0, EPSILON);
        assertEquals(prop.getValue(), 1.0, EPSILON);
        assertEquals(prop.getStep(), 0.1, EPSILON);
    }
}
