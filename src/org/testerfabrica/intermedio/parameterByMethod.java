package org.testerfabrica.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class parameterByMethod {
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

    @Test(dataProvider = "searchProvider")
    public void testMetodoA(String tester, String search) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(search);
        System.out.println("Welcome -> "+ tester + " your search word is -> "+ search);
        Thread.sleep(2000);

        String testValue = searchText.getAttribute("value");
        System.out.println("Test value is -> "+ testValue +" and is equal to "+ search);
        //se limpia el input para que no guarde registro de lo que esta escrito
        searchText.clear();

        //Verifica si el valor buscado en google es correcto
        Assert.assertTrue(testValue.equalsIgnoreCase(search));
    }

    @Test(dataProvider = "searchProvider")
    public void testMetodoB(String search) throws InterruptedException {
        WebElement searchText = driver.findElement(By.name("q"));
        searchText.sendKeys(search);
        Thread.sleep(2000);

        String testValue = searchText.getAttribute("value");
        System.out.println("Test value is -> "+ testValue +" and is equal to "+ search);
        //se limpia el input para que no guarde registro de lo que esta escrito
        searchText.clear();

        //Verifica si el valor buscado en google es correcto
        Assert.assertTrue(testValue.equalsIgnoreCase(search));
    }

    //Aca se van a utilizar los metodos como parametro de entrada
    @DataProvider(name = "searchProvider")
    public Object[][] getDataFromDataProvider(Method m){
        if (m.getName().equals("testMetodoA")){
            return new Object[][]{   //Le digo que me retorne un objeto
                    {"Fernando", "Google"},
                    {"Luis", "Yahoo"},
                    {"Sara", "Facebook"},
                    {"Lorena", "Amazon"}
            };
        }else{
            return new Object[][]{   //Le digo que me retorne un objeto
                    {"Mexico"},
                    {"China"},
                    {"Rusia"},
            };
        }


    }

}
