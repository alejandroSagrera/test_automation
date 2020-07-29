package acamica.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
public class CaptureScreenShot {
	
    public static void takeAScreenShot(WebDriver ldriver){
        File src= ((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File(".\\screenshots\\"+System.currentTimeMillis()+".png"));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

}
