package fschmidt.javad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author fschmidt
 */
public class FileUtils {

    public void copyFromJar(String resource, String endPath) throws URISyntaxException, FileNotFoundException, IOException{
//        File newFile = new File(getClass().getClassLoader().getResource(resource).toURI());
//        Files.copy(newFile.toPath(), new FileOutputStream(new File(endPath+"/"+resource)));
         InputStream newFile = getClass().getClassLoader().getResourceAsStream(resource);
        Files.copy(newFile, new File(endPath+"/"+resource).toPath());
    }
}
