package javad3;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
/*
    This class implements JBrowser and contains methods for saving
    images and animations from a browser URL to disk.
 */
public class WebDriverTools {
    private JBrowserDriver driver;
    //Path where the images/animations are to be saved.
    private final String OUTPUT_PATH = "C:\\Users\\marti\\Documents\\d3plots\\d3Plot";

    //Base URL of server, which creates the SVGs.
    private final String URL = "http://localhost:8000/";
    public WebDriverTools(){
        driver = new JBrowserDriver(Settings.builder().
                timezone(Timezone.EUROPE_BERLIN).build());
    }

    /*
    Method for capturing a screenshot and saving it to disk.
    @param chartId: Id of generated chart.
     */
    public void captureImage(String chartId){
        driver.get(URL+chartId);
        File outputFile = driver.getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(outputFile,new File(OUTPUT_PATH+chartId+".png"));
        }catch (IOException e){
            System.err.println("Error occurred during writing of image file.");
            e.printStackTrace();
        }
    }
    /*
    Method for closure of WebDriver.
     */
    public void terminate(){
        driver.quit();
    }
}
