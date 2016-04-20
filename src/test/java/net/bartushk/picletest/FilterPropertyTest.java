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

    @Test
    public void FilterProperty_CopyConstructor(){
        FilterProperty prop = new FilterProperty("TestName");
        prop.setMax(12); 
        prop.setMin(-12); 
        prop.setValue(11);
        prop.setStep(1);

        FilterProperty propCopy = new FilterProperty(prop);

        
        assertEquals(prop.getName(), propCopy.getName());
        assertEquals(prop.getValue(), propCopy.getValue(), EPSILON);
        assertEquals(prop.getMax(), propCopy.getMax(), EPSILON);
        assertEquals(prop.getMin(), propCopy.getMin(), EPSILON);
        assertEquals(prop.getStep(), propCopy.getStep(), EPSILON);
        //Also ensure a deep copy is made
        assertNotEquals(propCopy, prop);
    }

    @Test
    public void FilterProperty_SetValue_NoEffectWhenOutsideRange(){
        FilterProperty prop = new FilterProperty();
        double origValue = prop.getMax();
        prop.setValue(origValue);

        prop.setValue(prop.getMax() + 1);
        assertEquals(prop.getValue(), origValue, EPSILON); 
        prop.setValue(prop.getMin() - 1);
        assertEquals(prop.getValue(), origValue, EPSILON); 
    }
}
