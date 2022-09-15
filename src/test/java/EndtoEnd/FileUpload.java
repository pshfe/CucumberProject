package EndtoEnd;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileUpload {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		WebDriverManager.chromedriver().setup();
		HashMap<String,Object>hm=new HashMap<String,Object>();
		hm.put("profile.default_content_settings.popups", 0);
		hm.put("download.default_directory", System.getProperty("user.dir"));
		
		ChromeOptions op= new ChromeOptions();
		op.setExperimentalOption("prefs", hm);
		
		WebDriver dr= new ChromeDriver(op);
		dr.get("https://www.freeconvert.com/pdf-to-jpg");
		dr.findElement(By.cssSelector("#upload-file-button")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\sidde\\OneDrive\\Desktop\\upload.exe");
		Thread.sleep(1000);
		dr.findElement(By.cssSelector(".file-input-dropdown__action__convert")).click();
		WebDriverWait w =new WebDriverWait(dr,Duration.ofSeconds(1000));
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".file-list-item__status")));
		dr.findElement(By.cssSelector(".download-action__button")).click();
		File f= new File(System.getProperty("user.dir")+"/poojaloan.pdf.jpg");
		Thread.sleep(5000);
		if(f.exists())
		{
			System.out.println("File found");
			if(f.delete())
			{
				System.out.println("File Deleted");
			}
		
			
		}
		
		
	}

}
