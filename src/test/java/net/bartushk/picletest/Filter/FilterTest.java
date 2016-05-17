package net.bartushk.picletest.Filter;

import java.util.List;

import org.junit.Test;

import org.opencv.core.Mat;

import net.bartushk.picle.Filter.*;
import static net.bartushk.picletest.TestUtils.EPSILON;
import static org.junit.Assert.*;


public class FilterTest
{
    private class MockFilter extends Filter
    {
        public FilterProperty prop1;
        public FilterProperty prop2;

        public MockFilter(){
            super();    
            prop1 = new FilterProperty("Test1");
            prop2 = new FilterProperty("Test2");

            prop1.setValue(20);
            prop1.setMax(100);
            prop2.setValue(-20);
            prop2.setMin(-100);

            properties.put("Test1", prop1);
            properties.put("Test2", prop2);

        }

        public Mat applyFilter(Mat inputImage){
            return null;
        }
    }

    @Test
    public void Filter_DefaultConstructor(){
        Filter filter = new MockFilter();   
        assertNotNull(filter);
    }

    @Test
    public void Filter_getPropertyNames_ReturnsPropertyNames(){
        Filter filter = new MockFilter();

        List<String> names = filter.getPropertyNames();

        assertTrue(names.contains("Test1"));
        assertTrue(names.contains("Test2"));
    }

    @Test
    public void Filter_setProperty_SetsPropertyValue(){
        MockFilter filter = new MockFilter();

        filter.setProperty("Test1", 12.0);
        filter.setProperty("Test2", -22.0);
        
        assertEquals(filter.prop1.getValue(), 12.0, EPSILON);
        assertEquals(filter.prop2.getValue(), -22.0, EPSILON);
    }

    @Test
    public void Filter_getProperty_GetsCorrectProperty(){
        MockFilter filter = new MockFilter();
        
        FilterProperty prop1 = filter.getProperty("Test1");
        FilterProperty prop2 = filter.getProperty("Test2");
        FilterProperty propNull = filter.getProperty("TestAsdf");

        assertEquals(prop1.getName(), filter.prop1.getName());
        assertEquals(prop2.getName(), filter.prop2.getName());
        assertNull(propNull);
    }

    @Test
    public void Filter_getProperty_ReturnsDeepCopy(){
        MockFilter filter = new MockFilter();
        
        FilterProperty prop1 = filter.getProperty("Test1");
        prop1.setName("TestName");

        assertEquals(filter.prop1.getName(), "Test1");
    }
}
