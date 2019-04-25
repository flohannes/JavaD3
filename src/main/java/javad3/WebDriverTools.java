package javad3;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
    public void captureImage(String chartId, int width, int heigth){
        driver.get(URL+chartId);
        File outputFile = driver.getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(outputFile,new File(OUTPUT_PATH+chartId+".png"));
            cropImage(OUTPUT_PATH+chartId+".png",width,heigth);
        }catch (IOException e){
            System.err.println("Error occurred during writing of image file.");
            e.printStackTrace();
        }
    }
    /*
         Method for cropping an Image. Returns a subimage of the given one.
         @params : imagePath
         @params : width
         @params : height
     */
    public void cropImage(String imagePath,int width, int height){
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(imagePath));
            // X coordinate,Y coordinate, width, height
            BufferedImage croppedImage = bufferedImage.getSubimage(0,0, width+70, height+70);
            ImageIO.write(croppedImage, "png",new File(imagePath));
        } catch (IOException e) {
            System.err.println("Error when reading image with path: "+ imagePath);
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
