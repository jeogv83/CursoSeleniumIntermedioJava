package org.testerfabrica.intermedio.report;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//Para utilizar la clase JyperionLisenert hay que crear la anotación y llamarla
@Listeners(JyperionListener.class)
//ahora hacemos que la clase PdfEmail herede metodos de baseClass,
// se hace agregando  "extends" y el nombre de la clase en este caso es baseClass
public class PdfEmail extends baseClass{

    WebDriver driver = getDriver();

    @Test
    public void testOne (){
        driver.get("https://www.google.com.mx/");
        //Forzo a que el test falle
        Assert.assertTrue(false);

    }

    @Test
    public void testTwo (){
        driver.get("https://www.facebook.com");
        Assert.assertTrue(true);
    }

    @Test
    public void testThree (){
        driver.get("https://titaniumsolutions.org/");
        Assert.assertTrue(false);
    }

    @AfterTest
    public void closeBrowser (){
        driver.quit();
    }

    @AfterSuite
    public void sendEmail (){
        sendPdfReportByEmail("titaniumsoltest@gmail.com", "titanium619", "titaniumsoltest@gmail.com","Jennifer PDF Reort", "Please adjuntar el Reporte en PDF");

    }

}
