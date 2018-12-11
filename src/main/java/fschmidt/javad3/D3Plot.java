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

    private final Version[] neededVersions;

    public enum Version {
        V3("d3.v3.min.js"), V4("d3.v4.min.js");

        private Version(String fileName) {
            this.fileName = fileName;
        }
        private final String fileName;

        public String getFileName() {
            return fileName;
        }
    };

    public D3Plot(Version... neededVersions) {
        this.neededVersions = neededVersions;
    }

    protected void addD3Lib(String path) throws IOException, URISyntaxException {
        String targetPath = path.substring(0, path.lastIndexOf("/"));
        File f = new File(targetPath);
        if (f.exists() && !f.isDirectory()) {
        } else {
            for (Version v : neededVersions) {
                new FileUtils().copyFromJar(v.getFileName(), targetPath);
            }
        }
    }

    protected void removeD3Lib(String path) {
        for (Version v : neededVersions) {
            String targetPath = path.substring(0, path.lastIndexOf("/")) + "/" + v.getFileName();
            File f = new File(targetPath);
            f.delete();
        }
    }

    public void savePng(String path) throws URISyntaxException, FileNotFoundException, IOException {
        addD3Lib(path);
        PrintWriter writer = new PrintWriter(path + ".html", "UTF-8");
        String[] jsFiles = new String[neededVersions.length];
        for(int i = 0; i < jsFiles.length;i++){
            jsFiles[i] = neededVersions[i].getFileName();
        }
        writer.print(getJavascriptFile(jsFiles));
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

    protected abstract String getJavascriptFile(String... d3jsPath);
}
