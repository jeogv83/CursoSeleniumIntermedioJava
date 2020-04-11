package org.testerfabrica.intermedio.video;


import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Va a heredar metodos de la clase "ScreenRecorder" de la libreria monte
//Luego agrego un constructor, en este caso el "SpecializedScreenRecorder", haciendo clic en el bombillo o raya roja
public class SpecializedScreenRecorder extends ScreenRecorder {
    /*GraphicsConfiguration: configuración de las graficas de a imagen que se va a desplegar
    * captureArea: Area de captura, tamaño que va a ocupar de captura
    * Format fileFormat. formato del archivo
    * Format screenFormat: formato de la pantalla
    * Format mouseFormat: formato del mouse, por ejem que se vea o no se vea.
    * Format audioFormat: formato del audio, si se quiere o no se quiere audio
    *
    * */
    private String name; //Creo esta variable y la agrego en el constructor SpecializedScreenRecorder
    public SpecializedScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
                                     Format screenFormat, Format mouseFormat, Format audioFormat, File movieFolder,
                                     String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder); //Esto es para referirse a una variable local en un metodo
        //y una variable de la super clase ScreenRecorder
        this.name = name;
    }

    @Override //Sobreescribir
    protected  File createMovieFile (Format filFormat) throws IOException {
        //pregunto si existe mi variable movieFolder, si no existe que la cree
        if(!movieFolder.exists()){
            movieFolder.mkdirs();
        }else if (!movieFolder.isDirectory()){ //Si no es un directorio
            throw new IOException("\"" + movieFolder +"\" No es un directorio");
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        //Nombre del video
        return new File(movieFolder, name + "-" + dateFormat.format(new Date()) + "." + Registry.getInstance().getExtension(filFormat));

    }
}
