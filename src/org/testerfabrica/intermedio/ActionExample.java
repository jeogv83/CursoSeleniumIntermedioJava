package org.testerfabrica.intermedio;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ActionExample {
    static WebDriver driver;
    //El siguiente metodo es para identifcar codigo java lejible para Selenium y ejecutar codigo
    public static void main (String[] args){
        String chromePath = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromePath);
        String baseURL = "https://www.facebook.com/";

        driver = new ChromeDriver();
        driver.get(baseURL);
        driver.manage().window().maximize();

        try {

            WebElement txtUser = driver.findElement(By.id("email"));
            //Instancio objeto Action que debe venir de la libreria org.openqa.selenium.interaccion
            Actions builder = new Actions(driver);
            Action seriesOfActions = builder
                    .moveToElement(txtUser)
                    .click()
                    .keyDown(Keys.SHIFT) // se presiona la tecla shift
                    .sendKeys("hello") //Se escirbe en minuscula para ver la emulación de que se aprieta el shift y lo escribe en mayuscula
                    .keyUp(Keys.SHIFT)
                    .doubleClick()
                    .contextClick(txtUser) //clic derecho
                    .build(); // Una vez que se colocan todos lo actions se cierra con este comando

            seriesOfActions.perform(); //Para ejecutar las acciones
            Thread.sleep(2000);


            System.out.println("test passed: ");


        }catch (NoSuchElementException ne){
            System.out.println("Test fallo: No se encontro el elemento");
        }catch (Exception e){
            System.out.println("Test fallo: "+ e.getMessage());
        }finally {
            driver.close();
        }

    }
}
