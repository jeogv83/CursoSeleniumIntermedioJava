package org.testerfabrica.intermedio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class testNG {
    String baseURL = "http://www.newtours.demoaut.com/index.php";
    WebDriver driver; //Variable del tipo WebDriver
    String resultadoEsperado = "";
    String actualResult = "";
    String chromePath = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";

    //El sigueinte metodo es para que se ejecute antes de cualquier prueba que se haga
    @BeforeTest //es una anotación
    public void launchBrowser (){
        System.setProperty("webdriver.chrome.driver", chromePath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    //Se crea este metodo para que siempre verifique el titulo de la pagina, se puede usar en otros archivos
    @BeforeMethod
    public void verifyHomePageTitle(){
        resultadoEsperado = "Welcome: Mercury Tours";
        actualResult = driver.getTitle();
        //Con testNG existen aserciones que nospermiten verifcar si el test falla o no automaticamente, retorna true o false
        Assert.assertEquals(actualResult,resultadoEsperado,"Titulos no coincide"); //se usa Assert de testNG

    }

    //Este metodo se va a repetir despues de cada ejecución
    @AfterMethod
    public void goBackHomePage (){
        driver.findElement(By.linkText("Home")).click();
    }

    //Finalmente luego de que se ejecuten todos los metodos se va a ejecutar este
    @AfterTest
    public void tearDown (){
        driver.quit();
    }

    //Para verificar que la aplicación se esta comportanto segun lo esperado, es decir, es lo que se va a probar
    @Test(priority = 1)
    public void register(){
        driver.findElement(By.linkText("REGISTER")).click();
        resultadoEsperado = "Register: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,resultadoEsperado, "Titulos no coinciden");
    }

    @Test(priority = 0, enabled = false) //se ejecuta primeor el test con ek numero mas bajo
    // y con enable = false el metodo no se ejecuta
    public void support(){
        driver.findElement(By.linkText("SUPPORT")).click();
        resultadoEsperado = "Under Construction: Mercury Tours";
        actualResult = driver.getTitle();
        Assert.assertEquals(actualResult,resultadoEsperado,"Titulos no coinciden");
    }
}
