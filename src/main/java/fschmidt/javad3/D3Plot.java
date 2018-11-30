package fschmidt.javad3;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.openqa.selenium.OutputType;

/**
 *
 * @author fschmidt
 */
public abstract class D3Plot {

    protected void addD3Lib(String path) throws IOException, URISyntaxException {
        String targetPath = path.substring(0,path.lastIndexOf("/"));
        File f = new File(targetPath);
        if (f.exists() && !f.isDirectory()) {
        } else {
            new FileUtils().copyFromJar("d3.v3.min.js", targetPath);
        }
    }

    protected void removeD3Lib(String path) {
        String targetPath = path.substring(0,path.lastIndexOf("/")) + "/d3.v3.min.js";
        File f = new File(targetPath);
        f.delete();
    }

    public void savePng(String path) throws URISyntaxException, FileNotFoundException, IOException {
        addD3Lib(path);
        PrintWriter writer = new PrintWriter(path + ".html", "UTF-8");
        writer.print(getJavascriptFile("d3.v3.min.js"));
        writer.close();
        rederHtmlImage(path);
        new File(path + ".html").delete();
        removeD3Lib(path);
    }

    protected void rederHtmlImage(String path) throws IOException {
        JBrowserDriver driver = new JBrowserDriver(Settings.builder().timezone(Timezone.UTC).build());
        driver.get("file://" + path + ".html");
        byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
        Files.write(Paths.get(path), screenshot);
        driver.quit();
    }

    protected abstract String getJavascriptFile(String d3jsPath);
}
