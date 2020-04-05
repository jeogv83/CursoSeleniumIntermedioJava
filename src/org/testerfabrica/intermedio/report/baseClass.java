package org.testerfabrica.intermedio.report;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class baseClass {

    static WebDriver driver;
    static String chromePath = System.getProperty("user.dir")+"\\drivers\\chromedriver.exe";

    public static WebDriver getDriver(){
        if(driver == null){
            System.setProperty("webdriver.chrome.driver", chromePath);
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    //Metodo para generar screenShot, en este caso se usara para los errores
    public static void takeScreenShot(WebDriver driver, String fileWithPath) throws IOException {
        //Llamo a la interfaz TakesScreenshot
        TakesScreenshot scrShot = (TakesScreenshot)driver;
        //creo una variable tipo File
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Se indica el destino del archivo
        File destFile = new File(fileWithPath);
        //se indica que copie el print en la ruta especificada
        FileUtils.copyFile(srcFile,destFile);
    }

    //Metodo donde el repore PDF sera enviado vía email
    public static void sendPdfReportByEmail(String from, String pass, String to, String subject, String body){
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        //activar notificacion para poder enviar correo
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);        //inidcar host
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        //Indicar puerto de comunicación de donde saldra el email desde nuestra maquina
        props.put("mail.smtp.port", "587"); //Puerto de salida de correo 25, 465 y  587
        //Indicar la autenticación del user y el pass
        props.put("mail.snt.auth", "true");
        /* Todo esto se mete en un objeto de tipo session */
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session); // permite la transferencia de texto, archivos, video, audio ..

        try {
            //indica el destinatario que enviara el correo
            message.setFrom(new InternetAddress(from));
            //Indica a que correo llegara
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(subject);
            message.setText(body);
            BodyPart objectMessageBodyPart = new MimeBodyPart();
            objectMessageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(objectMessageBodyPart);
            objectMessageBodyPart = new MimeBodyPart();

            String fileName  = System.getProperty("user.dir") + "SeleniumIntermedio.pdf";

            //Creo un objeto data source para poder adjuntar el .pdf
            DataSource source = new FileDataSource(fileName);
            objectMessageBodyPart.setDataHandler(new DataHandler(source));
            objectMessageBodyPart.setFileName(fileName);
            multipart.addBodyPart(objectMessageBodyPart);

            message.setContent(multipart); //Se une todo para terminar la conexión de esta parte del email
            //creo un objeto del tipo transport
            Transport transport = session.getTransport("smtp");
            transport.connect(host,from,pass);//Conecto mi host con las cuentas de correo la de envío y la que recibe
            transport.sendMessage(message, message.getAllRecipients());//envio el mensaje
            transport.close(); // Cierra la conexión

        }catch (AddressException e){
            System.err.println("Problemas con la dirección de correo: "+ e.getMessage());
        }catch (MessagingException e){
            System.err.println("Problemas para conectar smtp, revisar host y puerto: "+ e.getMessage());
        }

    }
}
