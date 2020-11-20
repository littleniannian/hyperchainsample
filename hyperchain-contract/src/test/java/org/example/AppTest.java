package org.example;

import static org.junit.Assert.assertTrue;

import org.example.util.ComUtil;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void utilTest(){
        System.out.println(ComUtil.genKey("AAA","BBB"));
    }
}
