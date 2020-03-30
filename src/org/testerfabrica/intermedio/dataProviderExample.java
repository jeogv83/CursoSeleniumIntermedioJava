package org.testerfabrica.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class dataProviderExample {
    WebDriver driver;
    @BeforeTest
    public void setup (){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @DataProvider(name = "searchProvider")
    public Object[][] getDataFromDataProvider(){
        return new Object[][]{   //Le digo que me retorne un objeto
                {"Fernando", "Google"},
                {"Luis", "Yahoo"},
                {"Sara", "Gmail"},
                {"Lorena", "Amazon"}
        };
    }

    @Test(dataProvider = "searchProvider") //se debe llamar identico sino no lo reconce
    //Se debe indicar exactamente la cantidad de parametros que se van a ingresar,en nuestro caso son 2 por cada linea
    public void testMetodo(String tester, String search) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(search);
        System.out.println("Welcome -> "+ tester + " your search word is -> "+ search);
        Thread.sleep(3000);
        String testValue = searchText.getAttribute("value");
        System.out.println("Test value is -> "+ testValue +" and is equal to "+ search);
        //se limpia el input para que no guarde registro de lo que esta escrito
        searchText.clear();

       // Assert.assertTrue();
    }


}
