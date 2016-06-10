package net.bartushk.picletest.Core;

import java.util.List;

import org.junit.Test;


import net.bartushk.picle.Core.DiscreteStepProperty;
import net.bartushk.picle.Core.PropertiedObject;
import static net.bartushk.picletest.TestUtils.EPSILON;
import static org.junit.Assert.*;


public class PropertiedObjectTest
{
    private class MockPropertiedObject extends PropertiedObject
    {
        public DiscreteStepProperty prop1;
        public DiscreteStepProperty prop2;

        public MockPropertiedObject(){
            super();    
            prop1 = new DiscreteStepProperty("Test1");
            prop2 = new DiscreteStepProperty("Test2");

            prop1.setValue(20);
            prop1.setMax(100);
            prop2.setValue(-20);
            prop2.setMin(-100);

            properties.put("Test1", prop1);
            properties.put("Test2", prop2);

        }
    }

    @Test
    public void PropertiedObject_DefaultConstructor(){
        PropertiedObject filter = new MockPropertiedObject();   
        assertNotNull(filter);
    }

    @Test
    public void PropertiedObject_getPropertyNames_ReturnsPropertyNames(){
        PropertiedObject filter = new MockPropertiedObject();

        List<String> names = filter.getPropertyNames();

        assertTrue(names.contains("Test1"));
        assertTrue(names.contains("Test2"));
    }

    @Test
    public void PropertiedObject_setProperty_SetsPropertyValue(){
        MockPropertiedObject filter = new MockPropertiedObject();

        filter.setProperty("Test1", 12.0);
        filter.setProperty("Test2", -22.0);
        
        assertEquals(filter.prop1.getValue(), 12.0, EPSILON);
        assertEquals(filter.prop2.getValue(), -22.0, EPSILON);
    }

    @Test
    public void PropertiedObject_getProperty_GetsCorrectProperty(){
        MockPropertiedObject filter = new MockPropertiedObject();
        
        DiscreteStepProperty prop1 = filter.getProperty("Test1");
        DiscreteStepProperty prop2 = filter.getProperty("Test2");
        DiscreteStepProperty propNull = filter.getProperty("TestAsdf");

        assertEquals(prop1.getName(), filter.prop1.getName());
        assertEquals(prop2.getName(), filter.prop2.getName());
        assertNull(propNull);
    }

    @Test
    public void PropertiedObject_getProperty_ReturnsDeepCopy(){
        MockPropertiedObject filter = new MockPropertiedObject();
        
        DiscreteStepProperty prop1 = filter.getProperty("Test1");
        prop1.setName("TestName");

        assertEquals(filter.prop1.getName(), "Test1");
    }
}
