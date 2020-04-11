package org.testerfabrica.intermedio.video;


import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;

//Importo libreria estaticas

public class VideoRecorder {
    private ScreenRecorder screenRecorder;

    //Metodo para detener la grabación cuando se le indique
    private void stopRecording () throws IOException {
        this.screenRecorder.stop();
    }

    //Metodo para comezar a grabar
    private void startRecording (String videoPath) throws IOException {
        File file =  new File(videoPath); //Objeto del tipo file
        //Creo una variable del tipo Dimension, para saber la dimension de la pantalla
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Variables del tipo altura y ancho
        int width = screenSize.width;
        int height = screenSize.height;

        //Creo objeto del tipo rectagle
        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        //Mando a llamar a mi objeto screenRecorder
        this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
                new Format(FormatKeys.MediaType, FormatKeys.MediaType.FILE, FormatKeys.MimeTypeKey, FormatKeys.MIME_AVI),
                new Format(FormatKeys.MediaType, FormatKeys.MediaType.VIDEO, FormatKeys.EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, DepthKey, 24, FormatKeys.FrameRateKey, Rational.valueOf(15),
                QualityKey, 1.0F, KeyFrameIntervalKey, 15*60),
                new Format(FormatKeys.MediaType, FormatKeys.MediaType.VIDEO, FormatKeys.EncodingKey, "black"),
                FormatKeys.FrameRateKey, Rational.valueOf(30),
        "null", file, "ScreenRecorder");

        //Indicar que inicie la grabación
        this.screenRecorder.start();
    }

    @Test
    public void videoTest() throws IOException, AWTException  {
        VideoRecorder vr = new VideoRecorder();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Indico donde se va a grabar el video (ruta)
        vr.startRecording(System.getProperty("user.dir") + "\\video\\");

        driver.get("https://www.google.com/");

        WebElement txtSearch = driver.findElement(By.name("q"));
        txtSearch.sendKeys("Tester fabrica");
        txtSearch.submit();
        System.out.println("titulo de la pagina: " + driver.getTitle());
        driver.quit();

        //Detener la grabación
        vr.stopRecording();

    }
}
