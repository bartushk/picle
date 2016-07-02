package net.bartushk.picletest.Combiner;

import org.junit.Test;

import net.bartushk.picle.Combiner.AdditionCombiner;
import net.bartushk.picle.Core.DiscreteStepProperty;

import static net.bartushk.picletest.TestUtils.EPSILON;

import static org.junit.Assert.*;


public class AdditionCombinerTest extends BaseCombinerTest
{
    public AdditionCombinerTest(){
        super();
    }

    @Test
    public void AdditionCombiner_WhenConstructed_InputCountPropertyInitialized(){
        AdditionCombiner combiner = new AdditionCombiner(); 
        DiscreteStepProperty prop = combiner.getProperty("InputCount");
        assertEquals(prop.getName(), "InputCount");
        assertEquals(prop.getValue(), 2.0, EPSILON);
        assertEquals(prop.getMin(), 2.0, EPSILON);
        assertEquals(prop.getMax(), 6.0, EPSILON);
        assertEquals(prop.getStep(), 1.0, EPSILON);
    }

}
