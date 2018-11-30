package fschmidt.javad3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;

/**
 *
 * @author fschmidt
 */
public class FileUtils {

    public void copyFromJar(String resource, String endPath) throws URISyntaxException, FileNotFoundException, IOException{
        File newFile = new File(getClass().getClassLoader().getResource(resource).toURI());
        Files.copy(newFile.toPath(), new FileOutputStream(new File(endPath+"/"+resource)));
    }
}
