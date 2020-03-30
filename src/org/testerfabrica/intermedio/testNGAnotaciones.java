package org.testerfabrica.intermedio;

import org.testng.annotations.*;

public class testNGAnotaciones {

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Este metodo se ejecuta antes de una suite de pruebas");
    }

    @BeforeTest
    public void beforeTest(){
        System.out.println("Este metodo se ejecuta antes de las pruebas @Tests");
    }

    @BeforeClass
    public void beforeClass(){
        System.out.println("Este metodo se ejecuta antes de la clase");
    }

    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Se ejecuta antes de cada metodo de prueba @Test");
    }

    @Test
    public void testCase1(){
        System.out.println("Caso de prueba 1");
    }

    @Test
    public void testCase2(){
        System.out.println("Caso de prueba 2");
    }

    @AfterMethod
    public void afterMethod(){
        System.out.println("Se ejecuta despues de cada metodo de prueba");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("Este metodo se ejecuta despues de la clase");
    }

    @AfterTest
    public void afterTests(){
        System.out.println("Este metodo se ejecuta despues de todas las pruebas");
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println("Este metodo se ejecuta despues de la suite de pruebas");
    }

}
