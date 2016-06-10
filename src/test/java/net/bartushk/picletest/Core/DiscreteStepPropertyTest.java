package net.bartushk.picletest.Core;

import org.junit.Test;

import net.bartushk.picle.Core.DiscreteStepProperty;
import static net.bartushk.picletest.TestUtils.EPSILON;
import static org.junit.Assert.*;


public class DiscreteStepPropertyTest
{

    @Test
    public void DiscreteStepProperty_DefaultConstructor(){
        DiscreteStepProperty prop = new DiscreteStepProperty();

        assertEquals(prop.getName(), "No-Name");
        assertEquals(prop.getMax(), 1.0, EPSILON);
        assertEquals(prop.getMin(), 0.0, EPSILON);
        assertEquals(prop.getValue(), 1.0, EPSILON);
        assertEquals(prop.getStep(), 0.1, EPSILON);
    }

    @Test
    public void DiscreteStepProperty_StringConstructor(){
        DiscreteStepProperty prop = new DiscreteStepProperty("TestName");

        assertEquals(prop.getName(), "TestName");
        assertEquals(prop.getMax(), 1.0, EPSILON);
        assertEquals(prop.getMin(), 0.0, EPSILON);
        assertEquals(prop.getValue(), 1.0, EPSILON);
        assertEquals(prop.getStep(), 0.1, EPSILON);
    }

    @Test
    public void DiscreteStepProperty_FullConstructor(){
        DiscreteStepProperty prop = new DiscreteStepProperty("TestName");

        assertEquals(prop.getName(), "TestName");
        assertEquals(prop.getMax(), 1.0, EPSILON);
        assertEquals(prop.getMin(), 0.0, EPSILON);
        assertEquals(prop.getValue(), 1.0, EPSILON);
        assertEquals(prop.getStep(), 0.1, EPSILON);
    }

    @Test
    public void DiscreteStepProperty_CopyConstructor(){
        DiscreteStepProperty prop = new DiscreteStepProperty("TestName");
        prop.setMax(12); 
        prop.setMin(-12); 
        prop.setValue(11);
        prop.setStep(1);

        DiscreteStepProperty propCopy = new DiscreteStepProperty(prop);

        
        assertEquals(prop.getName(), propCopy.getName());
        assertEquals(prop.getValue(), propCopy.getValue(), EPSILON);
        assertEquals(prop.getMax(), propCopy.getMax(), EPSILON);
        assertEquals(prop.getMin(), propCopy.getMin(), EPSILON);
        assertEquals(prop.getStep(), propCopy.getStep(), EPSILON);
        //Also ensure a deep copy is made
        assertNotEquals(propCopy, prop);
    }

    @Test
    public void DiscreteStepProperty_SetValue_NoEffectWhenOutsideRange(){
        DiscreteStepProperty prop = new DiscreteStepProperty();
        double origValue = prop.getMax();
        prop.setValue(origValue);

        prop.setValue(prop.getMax() + 1);
        assertEquals(prop.getValue(), origValue, EPSILON); 
        prop.setValue(prop.getMin() - 1);
        assertEquals(prop.getValue(), origValue, EPSILON); 
    }
}
