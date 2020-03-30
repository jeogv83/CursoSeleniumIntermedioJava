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

public class parameterItestContext {
    WebDriver driver;
    @BeforeTest(groups = {"A","B"})
    public void setup (){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com");
    }

    @AfterTest(groups = {"A"})
    public void tearDown(){
        driver.quit();
    }

    @Test(dataProvider = "searchProvider", groups = "A")
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

    @Test(dataProvider = "searchProvider", groups = "B")
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

    //Se crea un archivo .xml
    @DataProvider(name = "searchProvider")
    public Object[][] getDataFromDataProvider(itestContext c){
        Object [][] groupArray = null;
        for (String group: c.getIncludedGroups()){
            if(group.equals("A")){
                groupArray = new Object[][]{
                        {"Fernando", "Google"},
                        {"Luis", "Yahoo"},
                        {"Sara", "Amazon"},
                        {"Lorena", "Gmail"}
                };
                break;
            }else if (group.equals("B")){
                groupArray = new Object[][]{
                        {"Mexico"},
                        {"Canada"},
                        {"Rusia"},
                        {"Japon"}
                };
            }
            break;
        }
        return groupArray;
    }

}
