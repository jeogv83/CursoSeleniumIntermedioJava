package org.testerfabrica.intermedio.listeners;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//Llamamos la clase que acabamos de crear que es realTimeReport
@Listeners(realTimeReport.class)
public class TestRealReport {

    //metodos que nos ayudaran a ver que sucede en la ejecución
    @Test
    public void testOne (){
        Assert.assertTrue(true);
    }

    @Test
    public void testTwo (){
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "testTwo")
    public void testThree (){
        Assert.assertTrue(true);
    }

}
