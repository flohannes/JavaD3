package fschmidt.javad3;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.openqa.selenium.OutputType;

/**
 *
 * @author fschmidt
 */
public class test2 {

    public static void main(String[] args) throws IOException {

        // You can optionally pass a Settings object here,
        // constructed using Settings.Builder
        JBrowserDriver driver = new JBrowserDriver(Settings.builder().
                timezone(Timezone.AMERICA_NEWYORK).build());

        // This will block for the page load and any
        // associated AJAX requests
        driver.get("file:///Volumes/sd/test.html");
        byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
        Files.write(Paths.get("/Volumes/sd/screenshot1.png"), screenshot);
        // You can get status code unlike other Selenium drivers.
        // It blocks for AJAX requests and page loads after clicks 
        // and keyboard events.
        System.out.println(driver.getStatusCode());

        // Returns the page source in its current state, including
        // any DOM updates that occurred after page load
        System.out.println(driver.getPageSource());

        // Close the browser. Allows this thread to terminate.
        driver.quit();
    }
}
