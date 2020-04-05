package org.testerfabrica.intermedio;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JavaScritpt {

    private WebDriver driver;
    String resultadoEsperado = "";
    String actualResult = "";
    String baseURL = "http://www.newtours.demoaut.com/";
    private JavascriptExecutor js; //variable del tipo javaScript, es una interfaz
    String pageLoadStatus = "";

    //Primer metodo javaScript para hacer highlight(realce) sobre los objetos
    private boolean highlight(WebElement element){
        js = (JavascriptExecutor)driver;
        for (int iCnt = 0; iCnt<3; iCnt++){
            try {
                //executeScrip: Este metodo permite ejecutar elcodigo JavaScript que tengamos como un String
                js.executeScript("arguments[0].setAttribute('style', 'background:red')", element); //cambia a color rojo
                Thread.sleep(1000);
                js.executeScript("arguments[0].setAttribute('style', 'background:')", element); //vuelve al color original


            }catch (Exception e){//Se agrega la expeción por si ocurre un error saber exactamente donde fue
                System.err.println("JavaScript metodo highlight | Descripción Exepción: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    //Metodo par hacer Scroll
    private boolean scrollWindow(){
        try {
            js = (JavascriptExecutor)driver;
            //metodo para hacer srcroll down, si quiero hacer scroll up el valos del parentesis debe ser negativo(0,-250)
            js.executeScript("window.scrollBy(0, 250)");

        }catch (Exception e){
            System.err.println("JavaScript metodo scrollWindow | Descripción Exepción: " + e.getMessage());
            return false;
        }
        return true;
    }

    //Este metodo es para que me indique que la pagina esta completamente cargada
    private boolean waitForPageToLoad(){
        try {
            do {
                js = (JavascriptExecutor) driver;
                pageLoadStatus = (String)js.executeScript("return.document.readyState"); //para saber si cargo completa la pag
            }while (!pageLoadStatus.equals("complete")); //Se ejecutara hasta que se cumpla complete
        }catch (Exception e){
            System.err.println("JavaScript metodo waitForPageToLoad | Descripción Exepción: " + e.getMessage());
            return false;
        }
        return true;
    }

    @BeforeTest
    public void launchBrowswer(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
        waitForPageToLoad();
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

   @Test(priority = 0)
    public void goToRegisterPage(){
        WebElement lnkRegister = driver.findElement(By.linkText("REGISTER"));
        Assert.assertTrue(highlight(lnkRegister));
        //hago click sobre el link register con JavaScritp
       js.executeScript("arguments[0].click();", lnkRegister);
       resultadoEsperado = "Register: Mercury Tours";
       actualResult = driver.getTitle();
       Assert.assertEquals(actualResult, resultadoEsperado);
       Assert.assertTrue(scrollWindow());

   }

   @Test(priority = 1)
    public void registerAnUser(){

        try{

            WebElement txtFirstName = driver.findElement(By.name("firstName"));
            highlight(txtFirstName);
            txtFirstName.sendKeys("Jennifer G");
            //Se selecciona un pais y se verifica que existan los elementos con highLight
            WebElement ddlCountry = driver.findElement(By.name("country"));
            highlight(ddlCountry);
            new Select(ddlCountry).selectByVisibleText("AUSTRIA");

            highlight(driver.findElement(By.id("email")));
            driver.findElement(By.id("email")).sendKeys("jeogv@mail.com");

            highlight(driver.findElement(By.name("password")));
            driver.findElement(By.name("password")).sendKeys("123");

            WebElement txtConfirmPass = driver.findElement(By.name("confirmPassword"));
            highlight(txtConfirmPass);
            txtConfirmPass.sendKeys("123");
            txtConfirmPass.submit();

            Assert.assertTrue(waitForPageToLoad());

            highlight(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[3]/a/font/b")));


        }catch (NoSuchElementException ne){
            Assert.fail("Test fallo: no se encontro el elemento");
        }catch (Exception e){
            Assert.fail("Test fallo: " + e.getMessage());
        }
       
   }



}
